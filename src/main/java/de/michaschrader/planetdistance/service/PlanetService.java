package de.michaschrader.planetdistance.service;

import de.michaschrader.planetdistance.planetposition.PlanetPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanetService {

    @Autowired
    public PlanetService() {}

    public long getDistanceBetweenPlanets(String firstPlanet, String secondPlanet, Date date) {
        return PlanetPosition.calculateDistance(firstPlanet, secondPlanet, date);
    }

    public List<String> getAllPlanets() {
        return List.of("Sun","Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune");
    }
}
