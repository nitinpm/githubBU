package util;

/**
 * Logger for debugging purposes
 * @author - Nitin Mahadik
 */
public class MyLogger {

    public enum DebugLevel {
        CONSTRUCTOR, FILE_PROCESSOR, NONE, STATE, INFO
    }

    private static DebugLevel debugLevel;


    /**
     * Sets the debug level as per the command input
     * @param levelIn - the input which tells what is the debugLevel
     */
    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 4:
                setDebugValue(DebugLevel.INFO);
                break;
            case 3:
                setDebugValue(DebugLevel.STATE);
                break;
            case 2:
                setDebugValue(DebugLevel.CONSTRUCTOR);
                break;
            case 1:
                setDebugValue(DebugLevel.FILE_PROCESSOR);
                break;
            default:
                setDebugValue(DebugLevel.NONE);
                break;
        }
    }

    /**
     * Helper to the main setDebugValue(int) method
     * @param levelIn - Sets the debubLevel to this param
     */
    private static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }


    /**
     * Writes the debug message on console
     * @param message - String to be displayed as debug message
     * @param levelIn - if this matches the set debugLevel during
     *                the run then only it prints
     *                the message on console.
     */
    public static void writeMessage(String message,
                                    DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}