package ro.academyplus.avaj.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileWriter("simulation.txt"));
        } catch (IOException e){
            System.out.println("Couldn't create file simulation.txt: " + e.getMessage());
        }
    }
        public static void log(String msg){
            writer.println(msg);
        }
        
        public static void close(){
            writer.close();
        }
}
