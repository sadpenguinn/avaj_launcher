package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

public interface Flyable {
    void updateConditions() throws PreconditionFailed;
    void registerTower(WeatherTower tower);
    long getId();
}
