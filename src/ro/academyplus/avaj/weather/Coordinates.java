package ro.academyplus.avaj.weather;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    //constructor
    public Coordinates(int p_longitude, int p_latitude, int p_height) {
        this.longitude = p_longitude;
        this.latitude = p_latitude;
        this.height = Math.min(100, Math.max(0, p_height));
    }
    //getters
    public int getLongitude() { return longitude; }
    public int getLatitude() { return latitude; }
    public int getHeight() { return height; }
}
