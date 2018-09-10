package util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Results class to display the output to stdOut
 *
 * @author - Nitin Mahadik
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {


    private String outputFileName;
    private PrintWriter writer;
    private String stringStore;
    /**
     * Constructor to initialise the private members
     */
    public Results(String outputFileNameIn) {
        MyLogger.writeMessage("Results constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        outputFileName = outputFileNameIn;
        stringStore = "";
    }


    /**
     * Prints output to the console.
     *
     * @param s String that needs to be printed
     */
    public void writeToStdout(String s) {
        System.out.print(s + "\t");
    }


    /**
     * Appends the stored result along with the parameter to the file
     * @param s         - The input result string.
     */
    public void writeToFile(String s) {
        try{
            writer = new PrintWriter(outputFileName);
            writer.println(s);
            writer.println(stringStore);
            MyLogger.writeMessage("Writing the output to the file", MyLogger.DebugLevel.INFO);
        }
        catch (FileNotFoundException fnf){
            System.out.println("Please check the argument for the output file.");
            fnf.printStackTrace();
        }
        finally {
            writer.close();
        }
    }


    /**
     * Appends new string to the data structure each time it is called
     * @param s This can be the test result that we want to store
     */
    public void storeNewResult(String s){
        stringStore = stringStore + "\n" + s;
    }

}
