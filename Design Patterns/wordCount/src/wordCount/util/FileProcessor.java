package util;


/**
 * FileProcessor for input file
 * It parses the file and returns a line by line
 *
 * @author - Nitin Mahadik
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * FileProcessor for input file
 * It parses the file and returns a line by line
 * integers as and when requested.
 *
 * @author - Nitin Mahadik and Aditya Tyagi
 */
public class FileProcessor {


    private BufferedReader bufferedReader;

    /**
     * Getter for bufferedReader reference
     * @return the reference to bufferedReader
     */
    private BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    /**
     * Setter for bufferedReader
     */
    private void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }


    /**
     * Parameterized contructor
     *
     * @param inputFileName - File name to be processed.
     */
    public FileProcessor(String inputFileName) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(inputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        this.setBufferedReader(br);
    }


    /**
     * Processes the file and returns a line
     *
     * @return one line at a time - string.
     */
    public String readLine() throws IOException {
        String line;
        try {
            line = this.getBufferedReader().readLine();
            if (line != null) {
                return line;
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        return null;
    }
}
