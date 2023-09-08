package github.com.sadpenguinn.avaj_launcher;

import java.util.Random;

public class WeatherProvider {

    private String weather[] = {"RAIN", "FOG", "SUN", "SNOW"};

    private static WeatherProvider weatherProvider = new WeatherProvider();

    private WeatherProvider() {}

    public static WeatherProvider get() {
        return WeatherProvider.weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        seed += new Random().nextInt(100);
        return (weather[Math.abs(seed) % 4]);
    }
}
