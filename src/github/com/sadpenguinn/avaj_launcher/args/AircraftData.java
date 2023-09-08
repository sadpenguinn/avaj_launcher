package github.com.sadpenguinn.avaj_launcher.args;

public class AircraftData {
    private String type;
    private String name;
    private int longitude;
    private int latitude;
    private int height;

    public AircraftData(String type, String name, int longitude, int latitude, int height) {
        this.type = type;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }
}
