package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {

    private HashMap<String, int[]> behaviour = new HashMap<String, int[]>() {
        {
            put("SUN", new int[] {10, 0, 2});
            put("RAIN", new int[] {5, 0, 0});
            put("FOG", new int[] {1, 0, 0});
            put("SNOW", new int[] {0, 0, 12});
        }};

    private HashMap<String, String> replics = new HashMap<String, String>() {
        {
            put("SUN",  "Cool, I'd like to land my helicopter here         (SUN)");
            put("RAIN", "I'm no longer a helicopter, but a submarine       (RAIN)");
            put("FOG",  "I don't see anything, am I already dead?          (FOG)");
            put("SNOW", "Excuse me, are there heaters where we're flying?  (SNOW)");
        }};

    public Helicopter(Long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void registerTower(WeatherTower tower) {
        this.tower = tower;
        this.tower.register(this);
        System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    @Override
    public void updateConditions() throws PreconditionFailed {
        boolean hasGrounded = this.fly(behaviour);

        if (hasGrounded) {
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + "There's no landing pad here but I have to ground now!");
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        } else {
            String newWeather = tower.getWeather(this.coordinates);
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + replics.get(newWeather));
        }
    }
}
