package ro.academyplus.avaj.simulator;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ro.academyplus.avaj.simulator.Simulator <scenario_file>");
            System.exit(1);
        }

//        System.out.println("Starting simulation with scenario file: " + scenarioFileName);
//        Simulation.run(scenarioFileName);
        String scenarioFileName = args[0];
        try {
            Simulation.run(scenarioFileName);
        } catch (Exception e) {
            System.err.println("Simulation failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
