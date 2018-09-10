package visitor;

import util.FileProcessor;
import treeForStrings.WordTreeInterface;
import util.MyLogger;
import util.Results;

import java.io.IOException;

/**
 * Visitor to populate the ADTs with the file data
 *
 * @author Aditya Tyagi
 */
public class PopulateVisitor implements Visitor {

    /*
     * File name of the input file which contain the data.
     */
    private String fileName;

    /**
     * Default constructor
     *
     * @param fileNameIn Input file name from which data is taken
     *                   and inserted in the ADTs
     */
    public PopulateVisitor(String fileNameIn) {
        MyLogger.writeMessage("PopulateVisitor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        fileName = fileNameIn;
    }


    /**
     * visit method to insert the words in the Tree map
     *
     * @param myWordMap Reference to the tree word map being visited
     */
    public void visit(WordTreeInterface myWordMap) {
        MyLogger.writeMessage("Populating: " + myWordMap.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);

        FileProcessor fileProc = new FileProcessor(fileName);
        try {
            String line = fileProc.readLine();

            while(line != null) {
                line = line.replaceAll("\\s+", " ");
                String splitLine[] = line.split(" ");
                for (String s : splitLine)
                    if(!s.matches("\\s+") && !s.matches(""))
                        myWordMap.addElement(s);

                line = fileProc.readLine();
            }

            }
        catch(IOException e) {
            System.out.println("File Not Found");
        }
    }
}
















