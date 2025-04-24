package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}