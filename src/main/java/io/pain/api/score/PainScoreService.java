package io.pain.api.score;

import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class PainScoreService {

  public static final String VERSION = "v0.1";

  public PainScoreResult calculate(PainScoreContext context) {
    int scoreData = toPercentage(computeDataScore(context));
    int scoreTime = toPercentage(computeTimeScore(context));
    int scoreRisk = toPercentage(computeRiskScore(context));

    int confidenceData = toPercentage(computeDataConfidence(context));
    int confidenceTime = toPercentage(computeTimeConfidence(context));
    int confidenceRisk = toPercentage(computeRiskConfidence(context));

    double scoreGlobalValue = 0.4 * scoreData + 0.3 * scoreTime + 0.3 * scoreRisk;
    double confidenceGlobalValue =
        0.4 * confidenceData + 0.3 * confidenceTime + 0.3 * confidenceRisk;

    int scoreGlobal = toPercentage(scoreGlobalValue);
    int confidenceGlobal = toPercentage(confidenceGlobalValue);

    return new PainScoreResult(
        scoreGlobal,
        scoreData,
        scoreTime,
        scoreRisk,
        confidenceGlobal,
        confidenceData,
        confidenceTime,
        confidenceRisk,
        VERSION);
  }

  private double computeDataScore(PainScoreContext context) {
    double totalStorageBytes = Math.max(0d, (double) context.estimatedTotalStorageBytes());
    double totalStorageGb = totalStorageBytes / (1024d * 1024d * 1024d);

    double volumeScore = (totalStorageGb / 250d) * 100d;

    int servicesCount = Math.max(0, context.servicesCount());
    double servicesScore;
    if (servicesCount <= 3) {
      servicesScore = 0d;
    } else if (servicesCount >= 10) {
      servicesScore = 100d;
    } else {
      servicesScore = ((servicesCount - 3d) / (10d - 3d)) * 100d;
    }

    return 0.7d * volumeScore + 0.3d * servicesScore;
  }

  private double computeTimeScore(PainScoreContext context) {
    double screenTimeHours = Math.max(0d, context.screenTimeHoursPerWeek());

    double minH = 7d;
    double maxH = 70d;

    double x;
    if (screenTimeHours <= minH) {
      x = 0d;
    } else if (screenTimeHours >= maxH) {
      x = 1d;
    } else {
      x = (screenTimeHours - minH) / (maxH - minH);
    }

    double baseTimeScore = Math.pow(x, 1.3d) * 100d;

    int interruptionsPerDay = Math.max(0, context.estimatedInterruptionsPerDay());
    double interruptionsNorm = clamp(interruptionsPerDay / 80d, 0d, 1d);
    double interruptionsScore = interruptionsNorm * 100d;

    return 0.8d * baseTimeScore + 0.2d * interruptionsScore;
  }

  private double computeRiskScore(PainScoreContext context) {
    if (!context.hasSecurityCheck()) {
      return 60d;
    }

    double base = 10d;

    if (!context.breachFound()) {
      base += 10d;
    } else {
      base += 30d;

      PainScoreContext.SecuritySeverity severity = context.maxSeverity();
      if (severity == null) {
        severity = PainScoreContext.SecuritySeverity.MEDIUM;
      }
      switch (severity) {
        case LOW -> base += 10d;
        case MEDIUM -> base += 25d;
        case HIGH -> base += 40d;
      }

      if (context.breachCount() >= 3) {
        base += 10d;
      }
    }

    long monthsSinceCheck = monthsSince(context.securityCheckedAt(), Instant.now());

    if (monthsSinceCheck <= 3) {
      base += 0d;
    } else if (monthsSinceCheck <= 6) {
      base += 5d;
    } else if (monthsSinceCheck <= 12) {
      base += 10d;
    } else {
      base += 20d;
    }

    return base;
  }

  private double computeDataConfidence(PainScoreContext context) {
    double base = 30d;

    if (context.hasEmailConnection()) {
      base += 30d;
    }
    if (context.hasCloudDeclaration()) {
      base += 20d;
    }
    if (context.hasDataSnapshotsOverOneMonth()) {
      base += 20d;
    }

    return base;
  }

  private double computeTimeConfidence(PainScoreContext context) {
    double base = 40d;

    if (context.hasRegularTimeUpdates()) {
      base += 20d;
    }
    if (context.hasObjectiveTimeSource()) {
      base += 20d;
    }

    return base;
  }

  private double computeRiskConfidence(PainScoreContext context) {
    if (!context.hasSecurityCheck()) {
      return 20d;
    }

    double base = 40d;

    long monthsSinceCheck = monthsSince(context.securityCheckedAt(), Instant.now());

    if (monthsSinceCheck <= 3) {
      base += 40d;
    } else if (monthsSinceCheck <= 6) {
      base += 30d;
    } else if (monthsSinceCheck <= 12) {
      base += 20d;
    } else {
      base += 10d;
    }

    return base;
  }

  private long monthsSince(Instant from, Instant to) {
    if (from == null || to == null) {
      return Long.MAX_VALUE / 2L;
    }
    if (to.isBefore(from)) {
      Instant tmp = from;
      from = to;
      to = tmp;
    }
    Duration duration = Duration.between(from, to);
    long days = duration.toDays();
    return days / 30L;
  }

  private int toPercentage(double value) {
    double clamped = clamp(value, 0d, 100d);
    return (int) Math.round(clamped);
  }

  private double clamp(double value, double min, double max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }
}
