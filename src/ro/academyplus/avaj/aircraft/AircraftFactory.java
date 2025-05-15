package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.Coordinates;

import ro.academyplus.avaj.simulator.Logger;

public class AircraftFactory {
    // Singleton instance
    private static final AircraftFactory instance = new AircraftFactory();
    private static long idCounter = 0;

    // Private constructor
    private AircraftFactory() {}

    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long id = ++idCounter;
        // Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (p_type) {
            case "Baloon":
                return new Baloon(id, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(id, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(id, p_name, p_coordinates);
            default:
                // System.out.println("Unknown aircraft type: " + p_type);
                Logger.log("Unknown aircraft type: " + p_type);
                return null;
        }
    }
}

