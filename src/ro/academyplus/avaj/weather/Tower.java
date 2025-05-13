package ro.academyplus.avaj.weather;

import ro.academyplus.avaj.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    //list of aircrafts registered as observers
    private List<Flyable> observers = new ArrayList<>();
    
    //method for registering aircraft in the list of the tower
    public void register(Flyable p_flyable) {
        if (!observers.contains(p_flyable))
            observers.add(p_flyable);
    }
    //method for deleting aircraft from the list of the tower
    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionsChanged() {
        //creating copy of list before iteration and updating conditions
        // to avoid errors in case when observers status changed at the same moment (landed for example)
        List<Flyable> copy = new ArrayList<>(observers);
        //updating conditions for each observer in copy
        for (Flyable flyable : copy) {
            flyable.updateConditions();
        }
    }
}
