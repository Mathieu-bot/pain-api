package io.pain.api.endpoint.rest.controller.pain;

import io.pain.api.gen.api.SnapshotsApi;
import io.pain.api.gen.model.DataSnapshot;
import io.pain.api.gen.model.DataSnapshotRequest;
import io.pain.api.gen.model.TimeUsageSnapshot;
import io.pain.api.gen.model.TimeUsageSnapshotRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SnapshotsController implements SnapshotsApi {

  @Override
  public ResponseEntity<DataSnapshot> apiMeSnapshotsDataPost(
      DataSnapshotRequest dataSnapshotRequest) {
    // TODO: Persist DataSnapshotEntity from request payload for the current authenticated user.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<DataSnapshot> apiMeSnapshotsDataLatestGet() {
    // TODO: Fetch the most recent DataSnapshotEntity for the current user and map to DTO.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<TimeUsageSnapshot> apiMeSnapshotsTimeUsagePost(
      TimeUsageSnapshotRequest timeUsageSnapshotRequest) {
    // TODO: Persist TimeUsageSnapshotEntity from request payload for the current authenticated
    // user.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<TimeUsageSnapshot> apiMeSnapshotsTimeUsageLatestGet() {
    // TODO: Fetch the most recent TimeUsageSnapshotEntity for the current user and map to DTO.
    return ResponseEntity.status(501).build();
  }
}
