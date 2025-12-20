package io.pain.api.endpoint.rest.controller.pain;

import io.pain.api.gen.api.ScoreApi;
import io.pain.api.gen.model.PainScorePreviewRequest;
import io.pain.api.gen.model.PainScoreResult;
import io.pain.api.gen.model.PainScoreSnapshot;
import io.pain.api.score.PainScoreContext;
import io.pain.api.score.PainScoreService;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreController implements ScoreApi {

  private final PainScoreService painScoreService;

  @Override
  public ResponseEntity<PainScoreResult> apiScorePreviewPost(
      PainScorePreviewRequest painScorePreviewRequest) {
    PainScoreContext context = toContext(painScorePreviewRequest);
    io.pain.api.score.PainScoreResult result = painScoreService.calculate(context);
    return ResponseEntity.ok(toGenResult(result));
  }

  @Override
  public ResponseEntity<PainScoreSnapshot> apiMeScorePost() {
    // TODO: Retrieve latest DataSnapshot and TimeUsageSnapshot for current authenticated user,
    // compute PainScore using PainScoreService, persist PainScoreSnapshotEntity,
    // and return the generated DTO.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<PainScoreSnapshot> apiMeScoreGet() {
    // TODO: Fetch the most recent PainScoreSnapshotEntity for the current user and map to DTO.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<List<PainScoreSnapshot>> apiMeScoreHistoryGet(
      OffsetDateTime from, OffsetDateTime to) {
    // TODO: Return all PainScoreSnapshotEntity for the current user,
    // optionally filtered by from/to timestamps, ordered by createdAt desc.
    return ResponseEntity.status(501).build();
  }

  // -------------------------------------------------------------------------
  // Mapping helpers (generated DTOs ↔ our domain)
  // -------------------------------------------------------------------------

  private PainScoreContext toContext(PainScorePreviewRequest req) {
    return new PainScoreContext(
        req.getEstimatedTotalStorageBytes(),
        req.getServicesCount(),
        req.getScreenTimeHoursPerWeek(),
        req.getEstimatedInterruptionsPerDay(),
        req.getHasSecurityCheck(),
        req.getBreachFound(),
        req.getBreachCount(),
        req.getMaxSeverity() != null
            ? PainScoreContext.SecuritySeverity.valueOf(req.getMaxSeverity().name())
            : null,
        req.getSecurityCheckedAt() != null ? req.getSecurityCheckedAt().toInstant() : null,
        req.getHasEmailConnection(),
        req.getHasCloudDeclaration(),
        req.getHasDataSnapshotsOverOneMonth(),
        req.getHasRegularTimeUpdates(),
        req.getHasObjectiveTimeSource());
  }

  private PainScoreResult toGenResult(io.pain.api.score.PainScoreResult domain) {
    PainScoreResult gen = new PainScoreResult();
    gen.setScoreGlobal(domain.scoreGlobal());
    gen.setScoreData(domain.scoreData());
    gen.setScoreTime(domain.scoreTime());
    gen.setScoreRisk(domain.scoreRisk());
    gen.setConfidenceGlobal(domain.confidenceGlobal());
    gen.setConfidenceData(domain.confidenceData());
    gen.setConfidenceTime(domain.confidenceTime());
    gen.setConfidenceRisk(domain.confidenceRisk());
    gen.setVersion(domain.version());
    return gen;
  }

  // Placeholder for future snapshot mapping (not used yet)
  private PainScoreSnapshot toGenSnapshot(io.pain.api.score.PainScoreResult domain) {
    PainScoreSnapshot snap = new PainScoreSnapshot();
    snap.setId(UUID.randomUUID());
    snap.setCreatedAt(Instant.now().atOffset(ZoneOffset.UTC));
    snap.setScoreGlobal(domain.scoreGlobal());
    snap.setScoreData(domain.scoreData());
    snap.setScoreTime(domain.scoreTime());
    snap.setScoreRisk(domain.scoreRisk());
    snap.setConfidenceGlobal(domain.confidenceGlobal());
    snap.setConfidenceData(domain.confidenceData());
    snap.setConfidenceTime(domain.confidenceTime());
    snap.setConfidenceRisk(domain.confidenceRisk());
    snap.setVersion(domain.version());
    return snap;
  }
}
