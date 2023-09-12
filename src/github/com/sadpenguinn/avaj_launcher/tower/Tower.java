package github.com.sadpenguinn.avaj_launcher.tower;

import github.com.sadpenguinn.avaj_launcher.aircraft.Flyable;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionChanged() throws PreconditionFailed {
        List<Flyable> observersLinks = new ArrayList<>();
        this.observers.forEach((f) -> observersLinks.add(f));
        for (Flyable f : observersLinks) {
            f.updateConditions();
        }
    }

    public int aircraftPoolSize() {
        return this.observers.size();
    }
}
