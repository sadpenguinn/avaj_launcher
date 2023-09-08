package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {

    private HashMap<String, int[]> behaviour = new HashMap<String, int[]>() {
        {
            put("SUN", new int[] {2, 0, 4});
            put("RAIN", new int[] {0, 0, -5});
            put("FOG", new int[] {0, 0, -3});
            put("SNOW", new int[] {0, 0, -15});
        }};

    private HashMap<String, String> replics = new HashMap<String, String>() {
        {
            put("SUN",  "I forgot my sunscreen, we're going to die!            (SUN)");
            put("RAIN", "I always wanted a pool, but not in my balloon basket  (RAIN)");
            put("FOG",  "We're underwater, right?                              (FOG)");
            put("SNOW", "I'm leaving this airline if I survive                 (SNOW)");
        }};

    public Baloon(Long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void registerTower(WeatherTower tower) {
        this.tower = tower;
        this.tower.register(this);
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    @Override
    public void updateConditions() throws PreconditionFailed {
        boolean hasGrounded = this.fly(behaviour);

        if (hasGrounded) {
            System.out.println("Baloon#" + this.name + "(" + this.id + "): " + "Crash landing! I hope this balloon can cushion the crash!");
            System.out.println("Baloon#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        } else {
            String newWeather = tower.getWeather(this.coordinates);
            System.out.println("Baloon#" + this.name + "(" + this.id + "): " + replics.get(newWeather));
        }
    }
}
