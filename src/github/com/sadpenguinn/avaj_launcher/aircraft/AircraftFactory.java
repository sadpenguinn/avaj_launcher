package github.com.sadpenguinn.avaj_launcher.aircraft;

import github.com.sadpenguinn.avaj_launcher.Coordinates;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;

public class AircraftFactory {

    private long aircraftIdCounter = 0;

    private static AircraftFactory factory = new AircraftFactory();

    private AircraftFactory() {}

    public static AircraftFactory get() {
        return AircraftFactory.factory;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) throws PreconditionFailed {

        if (type.equalsIgnoreCase("helicopter")) {
            return new Helicopter(getNextAircraftId(), name, coordinates);
        }
        else if (type.equalsIgnoreCase("jetplane")) {
            return new JetPlane(getNextAircraftId(), name, coordinates);
        }
        else if (type.equalsIgnoreCase("baloon")) {
            return new Baloon(getNextAircraftId(), name, coordinates);
        }

        throw new PreconditionFailed("Unknown aircraft type: " + type);
    }

    private long getNextAircraftId() {
        return aircraftIdCounter++;
    }
}
