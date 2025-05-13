package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.WeatherTower;
import ro.academyplus.avaj.weather.Coordinates;

import ro.academyplus.avaj.simulator.Logger;

public class Helicopter extends Aircraft implements Flyable {
    //field holds a reference to a WeatherTower instance.
    private WeatherTower weatherTower;
    
    //constructor
    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    //overriding interface Flyable method
    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather) {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                // System.out.println("Helicopter#" + name + "(" + id + "): I'm getting a tan up here! Maybe I should start selling sunscreen.");
                Logger.log("Helicopter#" + name + "(" + id + "): I'm getting a tan up here! Maybe I should start selling sunscreen.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                // System.out.println("Helicopter#" + name + "(" + id + "): This rain’s worse than a wet towel! Someone, please, hand me an umbrella!");
                Logger.log("Helicopter#" + name + "(" + id + "): This rain's worse than a wet towel! Someone, please, hand me an umbrella!");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight() );
                // System.out.println("Helicopter#" + name + "(" + id + "): What’s the point of having a rotor if you can’t even see where you're going?");
                Logger.log("Helicopter#" + name + "(" + id + "): What''s the point of having a rotor if you can't even see where you're going?");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                // System.out.println("Helicopter#" + name + "(" + id + "): Snowing again... great, now I’m a flying snowball!");
                Logger.log("Helicopter#" + name + "(" + id + "): Snowing again... great, now I'm a flying snowball!");
                break;
        }

        if (coordinates.getHeight() <= 0) {
            // System.out.println("Helicopter#" + name + "(" + id + ") landing.");
            Logger.log("Helicopter#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            // System.out.println("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
            Logger.log("Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }

    //overriding interface Flyable method
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        // System.out.println("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.");
        Logger.log("Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.");
    }
}
