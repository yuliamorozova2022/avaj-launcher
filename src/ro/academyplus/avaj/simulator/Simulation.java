//package ro.academyplus.avaj.simulator;
//
//import ro.academyplus.avaj.weather.*;
//import ro.academyplus.avaj.aircraft.*;
//import java.io.*;
//import java.util.*;
//
//public class Simulation {
//    private static final int MAX_HEIGHT = 100;
//    private static final int MIN_HEIGHT = 0;
//
//    public static void run(String scenarioFileName) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFileName))) {
//            int simulationSteps = Integer.parseInt(reader.readLine().trim());
//            WeatherTower weatherTower = new WeatherTower();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(" ");
//                if (parts.length != 5) {
//                    System.err.println("Invalid line in scenario file: " + line);
//                    continue;
//                }
//
//                String aircraftType = parts[0];
//                String name = parts[1];
//                int longitude = Integer.parseInt(parts[2]);
//                int latitude = Integer.parseInt(parts[3]);
//                int height = Integer.parseInt(parts[4]);
//
//                if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
//                    System.err.println("Invalid height for " + name + ": " + height);
//                    continue;
//                }
//
//                Coordinates coordinates = new Coordinates(longitude, latitude, height);
//                Flyable flyable = createAircraft(aircraftType, name, coordinates);
//                if (flyable != null) {
//                    flyable.registerTower(weatherTower);
//                }
//            }
//
//            for (int i = 0; i < simulationSteps; i++) {
//                weatherTower.changeWeather();
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading scenario file: " + e.getMessage());
//        } catch (NumberFormatException e) {
//            System.err.println("Invalid number format in scenario file: " + e.getMessage());
//        }
//    }
//
//    private static Flyable createAircraft(String type, String name, Coordinates coordinates) {
//        switch (type) {
//            case "Baloon":
//                return new Baloon(name, coordinates);
//            case "JetPlane":
//                return new JetPlane(name, coordinates);
//            case "Helicopter":
//                return new Helicopter(name, coordinates);
//            default:
//                System.err.println("Unknown aircraft type: " + type);
//                return null;
//        }
//    }
//}
package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.weather.*;
import ro.academyplus.avaj.aircraft.*;
import java.io.*;

public class Simulation {
    private static final int MAX_HEIGHT = 100;
    private static final int MIN_HEIGHT = 0;

    public static void run(String scenarioFileName) {
        int simulationSteps = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFileName))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                System.out.println("Error: Empty scenario file.");
                System.exit(1);
            }

            try {
                simulationSteps = Integer.parseInt(firstLine.trim());
                if (simulationSteps <= 0) {
                    System.out.println("Error: Number of simulation steps must be positive.");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: First line must be a valid integer.");
                System.exit(1);
            }

            WeatherTower weatherTower = new WeatherTower();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length != 5) {
                    System.out.println("Error: Invalid line format: " + line);
                    System.exit(1);
                }
                String type = parts[0];
                String name = parts[1];
                try {
                    int longitude = Integer.parseInt(parts[2]);
                    int latitude = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    if (longitude < 0 || latitude < 0) {
                        System.out.println("Error: Longitude and Latitude must be positive values: "+ line);
                        System.exit(1);
                    }
                    if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
                        System.out.println("Error: Invalid height (" + height + ") for " + name + ". Must be 0–100.");
                        System.exit(1);
                    }

                    Flyable flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
                    if (flyable == null) {
                        System.out.println("Error: Unknown aircraft type: " + type);
                        System.exit(1);
                    }

                    flyable.registerTower(weatherTower);

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format in coordinates: " + line);
                    System.exit(1);
                }
            }

            for (int i = 0; i < simulationSteps; i++) {
                weatherTower.changeWeather();
            }

        } catch (IOException e) {
            System.out.println("Error reading scenario file: " + e.getMessage());
            System.exit(1);
        }
    }
}
