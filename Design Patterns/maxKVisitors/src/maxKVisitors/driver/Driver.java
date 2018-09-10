package driver;

import util.*;
import visitor.MaxHeapVisitor;
import visitor.ModifiedBubbleSortVisitor;
import visitor.PopulateVisitor;
import visitor.Visitor;


/**
 * Driver code for the maxKVisitors project
 *
 * @author - Nitin Mahadik
 */
public class Driver {
    public static void main(String[] args) {

        String currDir = System.getProperty("user.dir");

        if (args.length == 3) {

            if (args[0] == null || args[0].equals("${arg0}") || !args[0].equals("input.txt")) {
                System.out.println("Incorrect or null argument for the input file. Exiting....");
                System.exit(1);
            }

            if (args[1] == null || args[1].equals("${arg1}")) {
                System.out.println("Incorrect or null argument for the K Value. Exiting....");
                System.exit(1);
            }

            if (args[2] == null || args[2].equals("${arg2}")) {
                System.out.println("Incorrect or null argument for the Debug Value. Exiting....");
                System.exit(1);
            }


            Integer kValue = null;
            Integer debugValue = null;

            try {
                kValue = Integer.parseInt(args[1]);
                debugValue = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.exit(1);
            }

            MyLogger.setDebugValue(debugValue);


            Results results = new Results();
            Visitor populateADT = new PopulateVisitor(currDir + "\\" + args[0], results);
            Visitor maxHeapVisitor = new MaxHeapVisitor(kValue, results);
            Visitor modBubbleSortVisitor = new ModifiedBubbleSortVisitor(kValue, results);

            MyADTInterface myArray1 = new MyArray(results);
            MyADTInterface myVector1 = new MyVector(results);
            MyADTInterface myArray2 = new MyArray(results);
            MyADTInterface myVector2 = new MyVector(results);

            myArray1.accept(populateADT);
            myArray1.accept(maxHeapVisitor);

            myVector1.accept(populateADT);
            myVector1.accept(maxHeapVisitor);

            myArray2.accept(populateADT);
            myArray2.accept(modBubbleSortVisitor);

            myVector2.accept(populateADT);
            myVector2.accept(modBubbleSortVisitor);

        } else {
            MyLogger.writeMessage("Number of parameters incorrect.", MyLogger.DebugLevel.INFO);
            System.exit(1);
        }
    }

}
