package github.com.sadpenguinn.avaj_launcher.tower;

import github.com.sadpenguinn.avaj_launcher.aircraft.Flyable;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tower {

    private Map<Long, Flyable> observers = new HashMap<>(); // TODO List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        this.observers.put(flyable.getId(), flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable.getId());
    }

    protected void conditionChanged() throws PreconditionFailed { // TODO: Удалить все эксепшены если нельзя
        List<Flyable> observersLinks = new ArrayList<>();
        this.observers.forEach((k, v) -> observersLinks.add(v));
        for (Flyable l : observersLinks) {
            l.updateConditions();
        }
    }
}
