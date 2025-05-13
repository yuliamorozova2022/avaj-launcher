package ro.academyplus.avaj.simulator;

import java.io.*;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            Logger.log("Usage: java ro.academyplus.avaj.simulator.Simulator <scenario_file>");
            Logger.close();
            System.exit(1);
        }

//        System.out.println("Starting simulation with scenario file: " + scenarioFileName);
//        Simulation.run(scenarioFileName);
        String scenarioFileName = args[0];
        try {
            Simulation.run(scenarioFileName);
        } catch (Exception e) {
            Logger.log("Simulation failed: " + e.getMessage());
            Logger.close();
            System.exit(1);
        }
        Logger.close();
    
    }
}
