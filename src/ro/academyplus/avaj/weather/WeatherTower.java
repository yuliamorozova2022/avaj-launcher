package ro.academyplus.avaj.weather;

public class WeatherTower extends Tower {
    //getter for new weather
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }
    //basically call for parent class method conditionsChanged()
    public void changeWeather() {
        this.conditionsChanged();
    }
}
