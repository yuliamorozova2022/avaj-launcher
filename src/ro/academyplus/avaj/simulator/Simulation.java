
package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.weather.*;
import ro.academyplus.avaj.aircraft.*;
import java.io.*;

public class Simulation {
    private static final int MAX_HEIGHT = 100;
    private static final int MIN_HEIGHT = 0;

    public static void run(String scenarioFileName) throws IOException {
        int simulationSteps = 0;

        // Opening the scenarioFileName. FileReader reads char by char, BufferedReader is a
        // wrap for FileReader to read line by line instead
        // try will automatically close the reader in case of problems
        try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFileName))) {

            // Read the number of simulation steps i.e. the 1st line in file
            String firstLine = reader.readLine();
            if (firstLine == null)
                throw new IllegalArgumentException("Scenario file is empty.");
            //first line verification
            try {
                simulationSteps = Integer.parseInt(firstLine.trim());
                if (simulationSteps <= 0)
                    throw new IllegalArgumentException("Number of simulation steps must be a positive integer.");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("First line must be a valid integer.", e);
            }
            // Creating the weather tower
            WeatherTower weatherTower = new WeatherTower();

            // Read file line by line and register each valid aircraft
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length != 5)
                    throw new IllegalArgumentException("Invalid line format: " + line);

                String type = parts[0];
                String name = parts[1];
                int longitude, latitude, height;
                try {
                    longitude = Integer.parseInt(parts[2]);
                    latitude = Integer.parseInt(parts[3]);
                    height = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Coordinates must be integers: " + line, e);
                }
                // coordinates check
                if (longitude < 0 || latitude < 0)
                    throw new IllegalArgumentException("Longitude and latitude must be positive: " + line);
                // height check
                if (height < MIN_HEIGHT || height > MAX_HEIGHT)
                    throw new IllegalArgumentException("Invalid height (" + height + ") for " + name + ". Must be 0–100.");
                // Create coordinates obj
                Coordinates coordinates = new Coordinates(longitude, latitude, height);
                // Create and register aircraft
                Flyable flyable = AircraftFactory.newAircraft(type, name, coordinates);
                if (flyable == null)
                    throw new IllegalArgumentException("Unknown aircraft type: " + type);
                flyable.registerTower(weatherTower);
            }

            // Start the simulation
            for (int i = 0; i < simulationSteps; i++) {
                weatherTower.changeWeather();
            }

        } // reader closes automatically
    }
}
