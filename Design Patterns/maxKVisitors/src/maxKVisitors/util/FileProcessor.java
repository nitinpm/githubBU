package util;

import java.io.*;

/**
 * FileProcessor for input file
 * It parses the file and returns a line by line
 * integers as and when requested.
 *
 * @author - Nitin Mahadik
 */
public class FileProcessor {

    private BufferedReader bufReader;

    /**
     * Parameterized contructor
     *
     * @param inputFileName - File name to be processed.
     */
    public FileProcessor(String inputFileName) {
        MyLogger.writeMessage("FileProcessor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);

        try {
            File file = new File(inputFileName);
            bufReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Processes the file and returns integers
     *
     * @return the integer contained in the file one line at a time.
     */
    public Integer nextInt() {
        Integer number = null;
        String line;

        try {
            line = bufReader.readLine();
            if (line != null)
                number = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return number;
    }

}
