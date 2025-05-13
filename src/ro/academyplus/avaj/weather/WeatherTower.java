package ro.academyplus.avaj.weather;

public class WeatherTower extends Tower {
    //getter for new weather
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }
    //basically call for parent class method conditionsChanged()
    public void changeWeather() {
        this.conditionsChanged();
    }
}
