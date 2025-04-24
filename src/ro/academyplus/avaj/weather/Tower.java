package ro.academyplus.avaj.weather;

import ro.academyplus.avaj.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (!observers.contains(flyable))
            observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        List<Flyable> copy = new ArrayList<>(observers);
        for (Flyable flyable : copy) {
            flyable.updateConditions();
        }
    }
}
