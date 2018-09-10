package util;

public class Logger {

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {
        CONSTRUCTOR, FILE_PROCESSOR, NONE, STATE, INFO
    }

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
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

    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage(String message,
                                    DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}