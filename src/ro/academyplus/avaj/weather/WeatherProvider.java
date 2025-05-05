package ro.academyplus.avaj.weather;

public class WeatherProvider {
    private static WeatherProvider instance = null;

    // Private constructor
    private WeatherProvider() {}

    // method for getting a single instance (singleton)
    public static WeatherProvider getProvider() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    // method for weather generation based on coordinates
    public String getCurrentWeather(Coordinates coordinates) {
        if (coordinates.getHeight() < 20) {
            return "RAIN";
        } else if (coordinates.getHeight() < 50) {
            return "FOG";
        } else if (coordinates.getHeight() < 80) {
            return "SUN";
        } else {
            return "SNOW";
        }
    }
}
