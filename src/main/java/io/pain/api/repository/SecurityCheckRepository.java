package io.pain.api.repository;

import io.pain.api.repository.model.SecurityCheckEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCheckRepository extends JpaRepository<SecurityCheckEntity, Long> {

  List<SecurityCheckEntity> findByUserIdOrderByCreatedAtDesc(UUID userId);

  SecurityCheckEntity findFirstByUserIdOrderByCreatedAtDesc(UUID userId);
}
