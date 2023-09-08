package github.com.sadpenguinn.avaj_launcher.tower;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.WeatherProvider;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.get().getCurrentWeather(coordinates);
    }

    public void changeWeather() throws PreconditionFailed {
        this.conditionChanged();
    }
}
