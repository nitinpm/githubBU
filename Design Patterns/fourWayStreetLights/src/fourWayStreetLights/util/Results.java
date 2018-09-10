package util;

import util.FileDisplayInterface;
import util.StdoutDisplayInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private String outputFileName;
    private String stringStore;
    private PrintWriter writer;

    /**
     * Constructor to initialise the private members
     * @param outputFileName The file where the results will be stored
     */
    public Results(String outputFileName){
        this.outputFileName = outputFileName;
        stringStore = "";
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
     * Prints output to the console.
     * @param s  - output string that needs to be printed
     */
    public void writeToStdout(String s){
        System.out.println(s);
    }

    /**
     * Appends new string to the data structure each time it is called
     * @param s This can be the test result that we want to store
     */
    public void storeNewResult(String s){
        stringStore = stringStore + "\n" + s;
    }

}
