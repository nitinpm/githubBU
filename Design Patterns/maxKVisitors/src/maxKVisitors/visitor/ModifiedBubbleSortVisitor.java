package visitor;

import util.MyADTInterface;
import util.MyLogger;
import util.Results;


/**
 * Class to Bubble sort the input data.
 *
 * @author Nitin Mahadik
 */
public class ModifiedBubbleSortVisitor implements Visitor {

    /*
    kValue for printing the top kValue number of elements
     */
    private int kValue;


    /*
    common results instance for writing to stdOut
     */
    private Results results;


    /**
     * Default constructor
     *
     * @param kValueIn  Value of K
     * @param resultsIn Common results instance
     */
    public ModifiedBubbleSortVisitor(int kValueIn, Results resultsIn) {
        kValue = kValueIn;
        results = resultsIn;
    }


    /**
     * visit method to perform BubbleSort on the ADT being referred.
     *
     * @param myAdt Reference to the ADT being visited.
     */
    public void visit(MyADTInterface myAdt) {
        MyLogger.writeMessage("ModifiedBubbleSortVisitor: Visiting: " + myAdt.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);
        BubbleSort(myAdt);
        myAdt.display(kValue);
    }


    /**
     * Bubble sort method
     *
     * @param myAdt Reference to ADT on which BubbleSort is applied
     */
    private void BubbleSort(MyADTInterface myAdt) {
        MyLogger.writeMessage("BubbleSorting: " + myAdt.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);
        int N = myAdt.size();
        int temp;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (myAdt.getMyADT().get(j) > myAdt.getMyADT().get(j + 1)) {
                    temp = myAdt.getMyADT().get(j);
                    myAdt.getMyADT().set(j, myAdt.getMyADT().get(j + 1));
                    myAdt.getMyADT().set(j + 1, temp);
                }
            }
        }
    }
}
