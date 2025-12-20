package io.pain.api.repository;

import io.pain.api.repository.model.PainScoreSnapshotEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PainScoreSnapshotRepository extends JpaRepository<PainScoreSnapshotEntity, Long> {

  List<PainScoreSnapshotEntity> findByUserIdOrderByCreatedAtDesc(UUID userId);

  PainScoreSnapshotEntity findFirstByUserIdOrderByCreatedAtDesc(UUID userId);
}
