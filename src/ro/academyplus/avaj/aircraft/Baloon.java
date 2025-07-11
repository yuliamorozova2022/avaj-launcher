package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.WeatherTower;
import ro.academyplus.avaj.weather.Coordinates;

import ro.academyplus.avaj.simulator.Logger;

public class Baloon extends Aircraft implements Flyable {
    //field holds a reference to a WeatherTower instance.
    private WeatherTower weatherTower;

    //constructor
    public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    //overriding interface Flyable method
    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                // System.out.println("Baloon#" + name + "(" + id + "): Let's fill the instagram with new pics.");
                Logger.log("Baloon#" + name + "(" + id + "): Let's fill the instagram with new pics.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                // System.out.println("Baloon#" + name + "(" + id + "): Damn you rain! You messed up my baloon.");
                Logger.log("Baloon#" + name + "(" + id + "): Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                // System.out.println("Baloon#" + name + "(" + id + "): I can't see anything!");
                Logger.log("Baloon#" + name + "(" + id + "): I can't see anything!");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                // System.out.println("Baloon#" + name + "(" + id + "): It's snowing. Better go for snowboarding...");
                Logger.log("Baloon#" + name + "(" + id + "): It's snowing. Better go for snowboarding...");
                break;
        }

        if (coordinates.getHeight() <= 0) {
            // System.out.println("Baloon#" + name + "(" + id + ") landing.");
            Logger.log("Baloon#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            // System.out.println("Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.");
            Logger.log("Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }
    
    //overriding interface Flyable method
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        // System.out.println("Tower says: Baloon#" + name + "(" + id + ") registered to weather tower.");
        Logger.log("Tower says: Baloon#" + name + "(" + id + ") registered to weather tower.");
    }
}
