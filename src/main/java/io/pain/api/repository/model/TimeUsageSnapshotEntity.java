package io.pain.api.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "time_usage_snapshot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeUsageSnapshotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Double screenTimeHoursPerWeek;

  @Column(nullable = false)
  private Integer estimatedInterruptionsPerDay;

  @Column(nullable = false)
  private Boolean hasRegularTimeUpdates;

  @Column(nullable = false)
  private Boolean hasObjectiveTimeSource;
}
