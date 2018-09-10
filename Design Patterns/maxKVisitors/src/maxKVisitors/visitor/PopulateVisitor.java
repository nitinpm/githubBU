package visitor;

import util.FileProcessor;
import util.MyADTInterface;
import util.MyLogger;
import util.Results;

/**
 * Visitor to populate the ADTs with the file data
 *
 * @author Nitin Mahadik
 */
public class PopulateVisitor implements Visitor {

    /*
     * File name of the input file which contain the data.
     */
    private String fileName;
    private Results results;

    /**
     * Default constructor
     *
     * @param fileNameIn Input file name from which data is taken
     *                   and inserted in the ADTs
     */
    public PopulateVisitor(String fileNameIn, Results resultsIn) {
        MyLogger.writeMessage("PopulateVisitor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        results = resultsIn;
        fileName = fileNameIn;
    }


    /**
     * visit method to insert the data in the ADT
     *
     * @param myAdt Reference to the ADT being visited
     *              i.e. MyArray or MyVector.
     */
    public void visit(MyADTInterface myAdt) {
        MyLogger.writeMessage("Populating: " + myAdt.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);

        FileProcessor fileProc = new FileProcessor(fileName);

        Integer value = fileProc.nextInt();

        while (value != null) {
            myAdt.addElement(value);
            value = fileProc.nextInt();
        }
    }
}
