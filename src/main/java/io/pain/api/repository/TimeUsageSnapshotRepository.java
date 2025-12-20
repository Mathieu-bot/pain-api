package io.pain.api.repository;

import io.pain.api.repository.model.TimeUsageSnapshotEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeUsageSnapshotRepository extends JpaRepository<TimeUsageSnapshotEntity, Long> {

  List<TimeUsageSnapshotEntity> findByUserIdOrderByCreatedAtDesc(UUID userId);

  TimeUsageSnapshotEntity findFirstByUserIdOrderByCreatedAtDesc(UUID userId);
}
