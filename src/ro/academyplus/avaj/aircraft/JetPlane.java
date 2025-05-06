package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.WeatherTower;
import ro.academyplus.avaj.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {
    //field holds a reference to a WeatherTower instance.
    private WeatherTower weatherTower;
    // constructor
    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
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
                System.out.println("JetPlane#" + name + "(" + id + "): Wow, it’s so sunny! I think I’m getting roasted in this metal can!");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                System.out.println("JetPlane#" + name + "(" + id + "): It’s raining! Does anyone know how to use windshield wipers on a plane:?");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight() );
                System.out.println("JetPlane#" + name + "(" + id + "): Flying through fog... I’m just guessing at this point. I hope there's no mountain ahead!");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                System.out.println("JetPlane#" + name + "(" + id + "): I can’t tell if I’m flying or ice-skating at this point. Someone call a mechanic!");
                break;
        }

        if (coordinates.getHeight() <= 0) {
            System.out.println("JetPlane#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
            System.out.println("Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.");
        }
    }
    //overriding interface Flyable method
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower.");
    }
}
