package io.pain.api.score;

import java.time.Instant;

public record PainScoreContext(
    long estimatedTotalStorageBytes,
    int servicesCount,
    double screenTimeHoursPerWeek,
    int estimatedInterruptionsPerDay,
    boolean hasSecurityCheck,
    boolean breachFound,
    int breachCount,
    SecuritySeverity maxSeverity,
    Instant securityCheckedAt,
    boolean hasEmailConnection,
    boolean hasCloudDeclaration,
    boolean hasDataSnapshotsOverOneMonth,
    boolean hasRegularTimeUpdates,
    boolean hasObjectiveTimeSource) {
  public enum SecuritySeverity {
    LOW,
    MEDIUM,
    HIGH
  }
}
