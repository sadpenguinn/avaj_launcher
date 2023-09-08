package github.com.sadpenguinn.avaj_launcher.args;

import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.exception.ResourceException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser {

    private int cycles;
    private List<AircraftData> aircraftArguments = new ArrayList<AircraftData>();

    public ArgumentsParser(String filename) throws ResourceException, PreconditionFailed {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            this.cycles = Integer.parseInt(reader.readLine());

            String nextLine = reader.readLine();
            while (nextLine != null) {
                if (nextLine.isEmpty()) {
                    throw new PreconditionFailed("Empty line");
                }
                String[] info = nextLine.split("\\s+");
                if (info.length != 5) {
                    throw new PreconditionFailed("Invalid line, cell count : " + info.length);
                }
                int longitude = Integer.parseInt(info[2]);
                int lattitude = Integer.parseInt(info[3]);
                int height = Integer.parseInt(info[4]);
                if (longitude < 0) {
                    throw new PreconditionFailed("Longitude must be positive");
                }
                if (lattitude < 0) {
                    throw new PreconditionFailed("Lattitude must be positive");
                }
                if (height < 0) {
                    throw new PreconditionFailed("Height must be positive");
                }
                AircraftData args = new AircraftData(info[0], info[1], longitude, lattitude, height);
                this.aircraftArguments.add(args);

                nextLine = reader.readLine();
            }

            reader.close();
        } catch (NumberFormatException | IOException e) {
            throw new ResourceException("Could not read file " + filename + ": " + e.getMessage());
        }
    }

    public int getCycles() {
        return this.cycles;
    }

    public List<AircraftData> getAircraftData() {
        return this.aircraftArguments;
    }
}
