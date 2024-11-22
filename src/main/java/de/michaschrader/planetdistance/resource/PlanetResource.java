package de.michaschrader.planetdistance.resource;

import de.michaschrader.planetdistance.model.Planet;
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
    public ResponseEntity<List<Planet>> getAllPlanets () {
        System.out.println("/all has been called");
        List<Planet> planets = planetService.findAllPlanets();
        return new ResponseEntity<>(planets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Planet> getPlanetsById (@PathVariable("id") Long id) {
        Planet planet = planetService.findPlanet(id);
        return new ResponseEntity<>(planet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) {
        Planet newPlanet = planetService.addPlanet(planet);
        return new ResponseEntity<>(newPlanet, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet) {
        Planet updatedPlanet = planetService.updatePlanet(planet);
        return new ResponseEntity<>(updatedPlanet, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable("id") Long id) {
        planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
