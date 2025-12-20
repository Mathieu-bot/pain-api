package io.pain.api.endpoint.rest.controller.pain;

import io.pain.api.gen.api.SecurityApi;
import io.pain.api.gen.model.SecurityCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController implements SecurityApi {

  @Override
  public ResponseEntity<SecurityCheck> apiMeSecurityChecksPost() {
    // TODO: Trigger security checks for the current authenticated user (e.g., email breach lookup),
    // persist SecurityCheckEntity, and return the result.
    return ResponseEntity.status(501).build();
  }

  @Override
  public ResponseEntity<SecurityCheck> apiMeSecurityChecksLatestGet() {
    // TODO: Fetch the most recent SecurityCheckEntity for the current user and map to DTO.
    return ResponseEntity.status(501).build();
  }
}
