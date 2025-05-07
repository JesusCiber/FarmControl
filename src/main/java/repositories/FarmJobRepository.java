package repositories;

import models.FarmJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmJobRepository extends JpaRepository<FarmJob, Long> {
    List<FarmJob> findByFarmId(Long farmId);
    List<FarmJob> findByDescriptionContaining(String keyword);
}
