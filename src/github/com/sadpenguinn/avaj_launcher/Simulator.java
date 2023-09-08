package github.com.sadpenguinn.avaj_launcher;

import github.com.sadpenguinn.avaj_launcher.aircraft.AircraftFactory;
import github.com.sadpenguinn.avaj_launcher.args.AircraftData;
import github.com.sadpenguinn.avaj_launcher.args.ArgumentsParser;
import github.com.sadpenguinn.avaj_launcher.exception.ArgsException;
import github.com.sadpenguinn.avaj_launcher.exception.PreconditionFailed;
import github.com.sadpenguinn.avaj_launcher.exception.ResourceException;
import github.com.sadpenguinn.avaj_launcher.tower.WeatherTower;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Simulator {

    private static String logFileName = "simulation.txt";

    public static void main(String[] args) {
        try {
            start(args);
        } catch (ArgsException e) {
            logError(e.getMessage());
        } catch (PreconditionFailed | ResourceException e) {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            try {
                Files.delete(Paths.get(logFileName));
            } catch (IOException ioe) {
                logError("Could not delete log file" + ioe.getMessage());
            }
            logError(e.getMessage());
        }
    }

    private static void start(String[] args) throws ArgsException, ResourceException, PreconditionFailed {
        if (args.length < 1) {
            throw new ArgsException("No arguments provided");
        }

        if (args.length > 1) {
            throw new ArgsException("Too many arguments provided");
        }

        File logFile = new File(logFileName);
        PrintStream logStream;
        try {
            logStream = new PrintStream(logFile);
        } catch (FileNotFoundException e) {
            throw new ResourceException("Could not create log file" + e.getMessage());
        }
        System.setOut(logStream);

        ArgumentsParser argumentsParser = new ArgumentsParser(args[0]);

        WeatherTower tower = new WeatherTower();

        int totalCycles = argumentsParser.getCycles();

        List<AircraftData> aircraftData = argumentsParser.getAircraftData();
        for (AircraftData arg : aircraftData) {
            AircraftFactory.get().newAircraft(arg.getType(), arg.getName(), new Coordinates(arg.getLongitude(), arg.getLatitude(), arg.getHeight())).registerTower(tower);
        }

        int cycles = totalCycles;
        while (cycles > 0) {
            System.out.println("\nCYCLE " + (totalCycles - cycles + 1) + "/" + totalCycles);
            tower.changeWeather();
            --cycles;
        }

        new PrintStream(new FileOutputStream(FileDescriptor.out)).println("Simulation complete successfully");
    }

    public static void logError(String message) {
        System.out.println(message);
    }
}