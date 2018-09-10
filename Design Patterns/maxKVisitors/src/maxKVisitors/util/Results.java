package util;

/**
 * Results class to display the output to stdOut
 *
 * @author - Nitin Mahadik
 */
public class Results implements StdoutDisplayInterface {

    /**
     * Constructor to initialise the private members
     */
    public Results() {
        MyLogger.writeMessage("Results constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    }


    /**
     * Prints output to the console.
     *
     * @param s String that needs to be printed
     */
    public void writeToStdout(String s) {
        System.out.print(s + "\t");
    }

}
