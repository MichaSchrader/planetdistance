package de.michaschrader.planetdistance.repo;

import de.michaschrader.planetdistance.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepo extends JpaRepository<Planet, Long> {
    void deletePlanetById(Long planetId);

    Optional<Planet> findPlanetById(Long planetId);
}
