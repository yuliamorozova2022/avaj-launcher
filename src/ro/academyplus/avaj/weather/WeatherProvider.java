package ro.academyplus.avaj.weather;

public class WeatherProvider {
    // Singleton instance
    private static final WeatherProvider instance = new WeatherProvider();
    
    private final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    // Private constructor
    private WeatherProvider() {}

    // method for getting a single instance (singleton)
    public static WeatherProvider getProvider() {
        return instance;
    }

    // method for weather generation based on coordinates
    public String getCurrentWeather(Coordinates p_coordinates) {
        if (p_coordinates.getHeight() < 20) {
            return weather[0];//"RAIN";
        } else if (p_coordinates.getHeight() < 50) {
            return weather[1];//"FOG";
        } else if (p_coordinates.getHeight() < 80) {
            return weather[2];//"SUN";
        } else {
            return weather[3];//"SNOW";
        }
    }
}
