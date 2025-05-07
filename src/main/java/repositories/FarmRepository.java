package repositories;


import models.Farm;
import models.FarmJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByOwnerId(Long ownerId);
    List<Farm> findByLocationContaining(String location);

}
