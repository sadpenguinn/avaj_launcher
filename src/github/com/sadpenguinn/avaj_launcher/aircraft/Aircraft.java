package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;

public class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower tower;

    protected Aircraft(Long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public long getId() {
        return this.id;
    }

    protected boolean fly(HashMap<String, int[]> behaviourByWeather) throws PreconditionFailed {
        String weather = tower.getWeather(this.coordinates);

        int[] behaviour = behaviourByWeather.get(weather);
        if (behaviour == null) {
            throw new PreconditionFailed("Unknown weather type: " + weather);
        }

        this.coordinates.setLongitude(this.coordinates.getLongitude() + behaviour[0]);
        this.coordinates.setLatitude(this.coordinates.getLatitude() + behaviour[1]);
        this.coordinates.setHeight(this.coordinates.getHeight() + behaviour[2]);

        return this.coordinates.getHeight() <= 0;
    }
}
