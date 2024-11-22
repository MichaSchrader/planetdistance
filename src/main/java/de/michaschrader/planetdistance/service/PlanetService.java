package de.michaschrader.planetdistance.service;

import de.michaschrader.planetdistance.exception.PlanetNotFoundException;
import de.michaschrader.planetdistance.model.Planet;
import de.michaschrader.planetdistance.planetposition.PlanetPosition;
import de.michaschrader.planetdistance.repo.PlanetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanetService {
    private final PlanetRepo planetRepo;

    @Autowired
    public PlanetService(PlanetRepo planetRepo) {
        this.planetRepo = planetRepo;
    }

    public long getDistanceBetweenPlanets(String firstPlanet, String secondPlanet, Date date) {
        return PlanetPosition.calculateDistance(firstPlanet, secondPlanet, date);
    }

    public Planet addPlanet(Planet planet) {
        return planetRepo.save(planet);
    }

    public List<Planet> findAllPlanets() {
        return planetRepo.findAll();
    }

    public Planet updatePlanet(Planet planet) {
        return planetRepo.save(planet);
    }

    public Planet findPlanet(Long planetId) {
        return planetRepo.findPlanetById(planetId)
                .orElseThrow(() -> new PlanetNotFoundException("No Planet with id:" + planetId + " was found in the database." ));
    }

    public void deletePlanet(Long planetId) {
        planetRepo.deletePlanetById(planetId);
    }
}
