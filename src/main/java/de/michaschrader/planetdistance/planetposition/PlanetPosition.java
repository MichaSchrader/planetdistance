package de.michaschrader.planetdistance.planetposition;

import java.util.Date;

import static de.michaschrader.planetdistance.planetposition.Earth.*;
import static de.michaschrader.planetdistance.planetposition.Jupiter.*;
import static de.michaschrader.planetdistance.planetposition.Mars.*;
import static de.michaschrader.planetdistance.planetposition.Mercury.*;
import static de.michaschrader.planetdistance.planetposition.Neptune.*;
import static de.michaschrader.planetdistance.planetposition.Saturn.*;
import static de.michaschrader.planetdistance.planetposition.Uranus.*;
import static de.michaschrader.planetdistance.planetposition.Venus.*;

public class PlanetPosition {
    private static class Position {
        public double x,y,z;
        Position(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public double distanceTo(Position other){
            return Math.sqrt(Math.pow(this.x - other.x, 2) +
                             Math.pow(this.y - other.y, 2) +
                             Math.pow(this.z - other.z, 2));

        }
    }

    public static long calculateDistance(String firstPlanet, String secondPlanet, Date date)
    {
        long millisSinceEpoch = date.getTime(); // time in milliseconds since 01 Jan 1970 (UNIX time)
        double julianDate = millisSinceEpoch / 86400000.0 + 2440587.5; // time in Standard Julian Date
        double timeInJulianMillennia = (julianDate - 2451545.0) / 365250; // time in Julian millennia reckoned from J2000 (2000-Jan-01-Sat 12:00:00 TT)
        Position firstPosition = positionOfPlanet(firstPlanet, timeInJulianMillennia);
        Position secondPosition = positionOfPlanet(secondPlanet, timeInJulianMillennia);
        assert firstPosition != null && secondPosition !=null;

        return Math.round(firstPosition.distanceTo(secondPosition) * 149597870.691); // in kilometer
    }

    private static Position positionOfPlanet(String planet, double time) {
        return switch (planet) {
            case "Sun" -> positionOfSun(time);
            case "Mercury" -> positionOfMercury(time);
            case "Venus" -> positionOfVenus(time);
            case "Earth" -> positionOfEarth(time);
            case "Mars" -> positionOfMars(time);
            case "Jupiter" -> positionOfJupiter(time);
            case "Saturn" -> positionOfSaturn(time);
            case "Uranus" -> positionOfUranus(time);
            case "Neptune" -> positionOfNeptune(time);
            default -> null;
        };
    }

    private static Position positionOfSun(double ignoredT)
    {
        return new Position(0, 0, 0);
    }

    private static Position positionOfMercury(double t)
    {
        double x = Mercury_A_X0(t) + Mercury_A_X1(t) + Mercury_A_X2(t) + Mercury_A_X3(t) + Mercury_A_X4(t) + Mercury_A_X5(t);
        double y = Mercury_A_Y0(t) + Mercury_A_Y1(t) + Mercury_A_Y2(t) + Mercury_A_Y3(t) + Mercury_A_Y4(t) + Mercury_A_Y5(t);
        double z = Mercury_A_Z0(t) + Mercury_A_Z1(t) + Mercury_A_Z2(t) + Mercury_A_Z3(t) + Mercury_A_Z4(t) + Mercury_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfVenus(double t)
    {
        double x = Venus_A_X0(t) + Venus_A_X1(t) + Venus_A_X2(t) + Venus_A_X3(t) + Venus_A_X4(t) + Venus_A_X5(t);
        double y = Venus_A_Y0(t) + Venus_A_Y1(t) + Venus_A_Y2(t) + Venus_A_Y3(t) + Venus_A_Y4(t) + Venus_A_Y5(t);
        double z = Venus_A_Z0(t) + Venus_A_Z1(t) + Venus_A_Z2(t) + Venus_A_Z3(t) + Venus_A_Z4(t) + Venus_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfEarth(double t)
    {
        double x = Earth_A_X0(t) + Earth_A_X1(t) + Earth_A_X2(t) + Earth_A_X3(t) + Earth_A_X4(t) + Earth_A_X5(t);
        double y = Earth_A_Y0(t) + Earth_A_Y1(t) + Earth_A_Y2(t) + Earth_A_Y3(t) + Earth_A_Y4(t) + Earth_A_Y5(t);
        double z = Earth_A_Z0(t) + Earth_A_Z1(t) + Earth_A_Z2(t) + Earth_A_Z3(t) + Earth_A_Z4(t) + Earth_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfMars(double t)
    {
        double x = Mars_A_X0(t) + Mars_A_X1(t) + Mars_A_X2(t) + Mars_A_X3(t) + Mars_A_X4(t) + Mars_A_X5(t);
        double y = Mars_A_Y0(t) + Mars_A_Y1(t) + Mars_A_Y2(t) + Mars_A_Y3(t) + Mars_A_Y4(t) + Mars_A_Y5(t);
        double z = Mars_A_Z0(t) + Mars_A_Z1(t) + Mars_A_Z2(t) + Mars_A_Z3(t) + Mars_A_Z4(t) + Mars_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfJupiter(double t)
    {
        double x = Jupiter_A_X0(t) + Jupiter_A_X1(t) + Jupiter_A_X2(t) + Jupiter_A_X3(t) + Jupiter_A_X4(t) + Jupiter_A_X5(t);
        double y = Jupiter_A_Y0(t) + Jupiter_A_Y1(t) + Jupiter_A_Y2(t) + Jupiter_A_Y3(t) + Jupiter_A_Y4(t) + Jupiter_A_Y5(t);
        double z = Jupiter_A_Z0(t) + Jupiter_A_Z1(t) + Jupiter_A_Z2(t) + Jupiter_A_Z3(t) + Jupiter_A_Z4(t) + Jupiter_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfSaturn(double t)
    {
        double x = Saturn_A_X0(t) + Saturn_A_X1(t) + Saturn_A_X2(t) + Saturn_A_X3(t) + Saturn_A_X4(t) + Saturn_A_X5(t);
        double y = Saturn_A_Y0(t) + Saturn_A_Y1(t) + Saturn_A_Y2(t) + Saturn_A_Y3(t) + Saturn_A_Y4(t) + Saturn_A_Y5(t);
        double z = Saturn_A_Z0(t) + Saturn_A_Z1(t) + Saturn_A_Z2(t) + Saturn_A_Z3(t) + Saturn_A_Z4(t) + Saturn_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfUranus(double t)
    {
        double x = Uranus_A_X0(t) + Uranus_A_X1(t) + Uranus_A_X2(t) + Uranus_A_X3(t) + Uranus_A_X4(t) + Uranus_A_X5(t);
        double y = Uranus_A_Y0(t) + Uranus_A_Y1(t) + Uranus_A_Y2(t) + Uranus_A_Y3(t) + Uranus_A_Y4(t) + Uranus_A_Y5(t);
        double z = Uranus_A_Z0(t) + Uranus_A_Z1(t) + Uranus_A_Z2(t) + Uranus_A_Z3(t) + Uranus_A_Z4(t) + Uranus_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }

    private static Position positionOfNeptune(double t)
    {
        double x = Neptune_A_X0(t) + Neptune_A_X1(t) + Neptune_A_X2(t) + Neptune_A_X3(t) + Neptune_A_X4(t) + Neptune_A_X5(t);
        double y = Neptune_A_Y0(t) + Neptune_A_Y1(t) + Neptune_A_Y2(t) + Neptune_A_Y3(t) + Neptune_A_Y4(t) + Neptune_A_Y5(t);
        double z = Neptune_A_Z0(t) + Neptune_A_Z1(t) + Neptune_A_Z2(t) + Neptune_A_Z3(t) + Neptune_A_Z4(t) + Neptune_A_Z5(t);
        return new Position(x, y, z);  // rectangular Heliocentric coordinates in AU
    }
}
