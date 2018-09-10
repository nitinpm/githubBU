package driver;

import treeForStrings.WordTreeMap;
import util.*;
import visitor.CountVisitor;
import visitor.PopulateVisitor;


/**
 * Driver code for the maxKVisitors project
 *
 * @author - Nitin Mahadik
 */
public class Driver {
    public static void main(String[] args) {

        String currDir = System.getProperty("user.dir");

        Results results;
        int NUM_ITERATIONS = 0, debugValue = 0;

        if (args.length == 4) {
            if (args[0] == null || args[0].equals("${arg0}")) {
                System.out.println("Null argument for the Input file. Exiting....");
                System.exit(1);
            }

            if (args[1] == null || args[1].equals("${arg1}")) {
                System.out.println("Null argument for the Output File. Exiting....");
                System.exit(1);
            }


            if (args[2] == null || args[2].equals("${arg2}")) {
                System.out.println("Null argument for the NUM_ITERATIONS. Exiting....");
                System.exit(1);
            }

            if (args[3] == null || args[3].equals("${arg3}")) {
                System.out.println("Null argument for the Debug Value. Exiting....");
                System.exit(1);
            }


            try {
                NUM_ITERATIONS = Integer.parseInt(args[2]);
                debugValue = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.exit(1);
            }

            results = new Results(currDir + "\\" + args[1]);
            System.out.println(currDir);
            MyLogger.setDebugValue(debugValue);
            int i = 0;
            long startTime = System.currentTimeMillis();


            while (i < NUM_ITERATIONS) {
                WordTreeMap wt = new WordTreeMap(results);
                PopulateVisitor pop = new PopulateVisitor(currDir + "\\" + args[0]);
                CountVisitor count = new CountVisitor(results);
                wt.accept(pop);
                wt.accept(count);
                i++;
            }

            long finishTime = System.currentTimeMillis();
            double totalTime = (double) (finishTime - startTime) / NUM_ITERATIONS;
            System.out.println("Total time: " + totalTime);
            results.storeNewResult("Total time: " + totalTime);
            results.writeToFile("Output:\n");
        }

        else{
            MyLogger.writeMessage("Number of parameters incorrect.", MyLogger.DebugLevel.INFO);
            System.exit(1);
        }
    }
}
