package util;

import java.io.*;

/**
 * FileProcessor for input/delete files
 * It parses the file and returns a line by line
 * Strings as and when requested.
 * @author - Nitin Mahadik
 */
public class FileProcessor {

    private BufferedReader bufReader;

    /**
     * Parameterized contructor
     * @param inputFileName - File name to be processed.
     */
    public FileProcessor(String inputFileName){

        try{
            File file = new File(inputFileName);
            bufReader = new BufferedReader(new FileReader(file));
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Processes the file and returns data inside
     * @return the data contained one line at a time.
     */
    public String readLine(){
        String line = "";

        try{
            line = bufReader.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
            return line;
        }

        return line;
    }

}
