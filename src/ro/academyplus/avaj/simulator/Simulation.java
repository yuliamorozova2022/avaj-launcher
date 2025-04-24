package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.weather.*;
import ro.academyplus.avaj.aircraft.*;
import java.io.*;
import java.util.*;

public class Simulation {
    private static final int MAX_HEIGHT = 100;
    private static final int MIN_HEIGHT = 0;

    public static void run(String scenarioFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFileName))) {
            int simulationSteps = Integer.parseInt(reader.readLine().trim());
            WeatherTower weatherTower = new WeatherTower();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 5) {
                    System.err.println("Invalid line in scenario file: " + line);
                    continue;
                }

                String aircraftType = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);

                if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
                    System.err.println("Invalid height for " + name + ": " + height);
                    continue;
                }

                Coordinates coordinates = new Coordinates(longitude, latitude, height);
                Flyable flyable = createAircraft(aircraftType, name, coordinates);
                if (flyable != null) {
                    flyable.registerTower(weatherTower);
                }
            }

            for (int i = 0; i < simulationSteps; i++) {
                weatherTower.changeWeather();
            }
        } catch (IOException e) {
            System.err.println("Error reading scenario file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in scenario file: " + e.getMessage());
        }
    }

    private static Flyable createAircraft(String type, String name, Coordinates coordinates) {
        switch (type) {
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                System.err.println("Unknown aircraft type: " + type);
                return null;
        }
    }
}
