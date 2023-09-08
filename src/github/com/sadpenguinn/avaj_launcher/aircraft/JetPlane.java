package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

    private HashMap<String, int[]> behaviour = new HashMap<String, int[]>() {
        {
            put("SUN", new int[] {0, 10, 2});
            put("RAIN", new int[] {0, 5, 0});
            put("FOG", new int[] {0, 1, 0});
            put("SNOW", new int[] {0, 0, 7});
        }};

    private HashMap<String, String> replics = new HashMap<String, String>() {
        {
            put("SUN",  "I forgot my sunglasses, we need to land for a while (SUN)");
            put("RAIN", "Air routes to warm countries attract me more        (RAIN)");
            put("FOG",  "Good thing I have radar, but I'm still scared       (FOG)");
            put("SNOW", "I'm not a snowmobile, I'm a jet plane!              (SNOW)");
        }};

    public JetPlane(Long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void registerTower(WeatherTower tower) {
        this.tower = tower;
        this.tower.register(this);
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    public void updateConditions() throws PreconditionFailed {
        boolean hasGrounded = this.fly(behaviour);

        if (hasGrounded) {
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + "Altitude too low, landing as safely as I can.");
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
            System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
                    + "] Latitude: [" + this.coordinates.getLatitude()
                    + "] Height: [" + this.coordinates.getHeight() + "]");
            this.tower.unregister(this);
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        } else {
            String newWeather = tower.getWeather(this.coordinates);
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): " + replics.get(newWeather));
        }
    }
}
