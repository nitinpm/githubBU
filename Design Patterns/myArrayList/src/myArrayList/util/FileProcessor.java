package myArrayList.util;

import java.io.*;
import java.util.Scanner;

public class FileProcessor {

    private Scanner sc;
    private BufferedReader bufReader;

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
