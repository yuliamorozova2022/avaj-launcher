package ro.academyplus.avaj.weather;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.min(100, Math.max(0, height)); // высота от 0 до 100
    }

    public int getLongitude() { return longitude; }
    public int getLatitude() { return latitude; }
    public int getHeight() { return height; }
}
