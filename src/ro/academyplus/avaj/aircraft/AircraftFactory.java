package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.Coordinates;

public class AircraftFactory {
    private static long idCounter = 0;

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                System.out.println("Unknown aircraft type: " + type);
                return null;
        }
    }
}

