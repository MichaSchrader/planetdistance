package de.michaschrader.planetdistance.resource;

import de.michaschrader.planetdistance.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//This is the "Controller"

@RestController
@RequestMapping("/planet")
public class PlanetResource {
    private final PlanetService planetService;

    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/distance/{firstPlanet}/{secondPlanet}/{dateInIsoFormat}")
    public ResponseEntity<Long> getPlanetDistance (@PathVariable("firstPlanet") String firstPlanet,
                                                   @PathVariable("secondPlanet") String secondPlanet,
                                                   @PathVariable("dateInIsoFormat") String dateInIsoFormat) {
        try {
            System.out.println("firstPlanet:" + firstPlanet + " secondPlanet:" + secondPlanet);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(dateInIsoFormat);
            System.out.println("time:" + date);
            Long distance = planetService.getDistanceBetweenPlanets(firstPlanet, secondPlanet, date);
            return new ResponseEntity<>(distance, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("calculation failed");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllPlanets () {
        List<String> planets = planetService.getAllPlanets();
        return new ResponseEntity<>(planets, HttpStatus.OK);
    }
}
