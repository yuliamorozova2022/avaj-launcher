package ro.academyplus.avaj.weather;

public class WeatherProvider {
    private static WeatherProvider instance = null;

    // Приватный конструктор для создания синглтона
    private WeatherProvider() {}

    // Метод для получения единственного экземпляра (синглтон)
    public static WeatherProvider getProvider() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    // Метод для генерации погоды на основе координат
    public String getCurrentWeather(Coordinates coordinates) {
        // Простой алгоритм для генерации погоды на основе координат.
        // Например, чем выше координата, тем чаще будет снег
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
