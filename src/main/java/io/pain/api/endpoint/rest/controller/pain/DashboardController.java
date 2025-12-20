package io.pain.api.endpoint.rest.controller.pain;

import io.pain.api.gen.api.DashboardApi;
import io.pain.api.gen.model.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController implements DashboardApi {

  @Override
  public ResponseEntity<DashboardResponse> apiMeDashboardGet() {
    // TODO: Build DashboardResponse for the current authenticated user:
    // - latest PainScoreSnapshot
    // - latest DataSnapshot and TimeUsageSnapshot
    // - latest SecurityCheck
    // Return aggregated view.
    return ResponseEntity.status(501).build();
  }
}
