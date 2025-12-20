package io.pain.api.repository;

import io.pain.api.repository.model.DataSnapshotEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSnapshotRepository extends JpaRepository<DataSnapshotEntity, Long> {

  List<DataSnapshotEntity> findByUserIdOrderByCreatedAtDesc(UUID userId);

  DataSnapshotEntity findFirstByUserIdOrderByCreatedAtDesc(UUID userId);
}
