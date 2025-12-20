package io.pain.api.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "security_check")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityCheckEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Boolean hasSecurityCheck;

  @Column(nullable = false)
  private Boolean breachFound;

  @Column(nullable = false)
  private Integer breachCount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SecuritySeverity maxSeverity;

  @Column(nullable = false)
  private Instant securityCheckedAt;

  @Column(nullable = false)
  private Boolean hasEmailConnection;

  @Column(nullable = false)
  private Boolean hasCloudDeclaration;

  @Column(nullable = false)
  private Boolean hasDataSnapshotsOverOneMonth;

  @Column(nullable = false)
  private Boolean hasRegularTimeUpdates;

  public enum SecuritySeverity {
    LOW,
    MEDIUM,
    HIGH
  }
}
