package io.pain.api.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "data_snapshot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSnapshotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Long estimatedTotalStorageBytes;

  @Column(nullable = false)
  private Integer servicesCount;

  @Column(nullable = false)
  private Boolean hasEmailConnection;

  @Column(nullable = false)
  private Boolean hasCloudDeclaration;

  @Column(nullable = false)
  private Boolean hasDataSnapshotsOverOneMonth;
}
