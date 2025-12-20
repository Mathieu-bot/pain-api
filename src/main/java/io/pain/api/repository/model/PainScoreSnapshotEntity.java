package io.pain.api.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "pain_score_snapshot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PainScoreSnapshotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Integer scoreGlobal;

  @Column(nullable = false)
  private Integer scoreData;

  @Column(nullable = false)
  private Integer scoreTime;

  @Column(nullable = false)
  private Integer scoreRisk;

  @Column(nullable = false)
  private Integer confidenceGlobal;

  @Column(nullable = false)
  private Integer confidenceData;

  @Column(nullable = false)
  private Integer confidenceTime;

  @Column(nullable = false)
  private Integer confidenceRisk;

  @Column(nullable = false)
  private String version;
}
