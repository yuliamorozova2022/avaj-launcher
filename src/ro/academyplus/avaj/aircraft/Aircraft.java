package ro.academyplus.avaj.aircraft;

import ro.academyplus.avaj.weather.Coordinates;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    //constructor
    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }
    // methhod for incrementing of idCounter
    private long nextId() {
        return ++idCounter;
    }
}
