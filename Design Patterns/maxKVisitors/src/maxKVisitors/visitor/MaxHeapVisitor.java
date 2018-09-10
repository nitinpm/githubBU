package visitor;

import util.MyADTInterface;
import util.MyLogger;
import util.Results;

/**
 * MaxHeapVisitor class to create a MaxHeap for the input data.
 *
 * @author Nitin Mahadik
 */
public class MaxHeapVisitor implements Visitor {

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
    public MaxHeapVisitor(int kValueIn, Results resultsIn) {
        kValue = kValueIn;
        results = resultsIn;
    }


    /**
     * visit method to create MaxHeap of the ADT being referred.
     *
     * @param myAdt Reference to the ADT being visited.
     */
    public void visit(MyADTInterface myAdt) {
        MyLogger.writeMessage("MaxHeapVisitor: Visiting: " + myAdt.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);
        HeapSort(myAdt);
        myAdt.display(kValue);
    }


    /**
     * HeapSort method
     *
     * @param myAdt Reference to the ADT on which HeapSort is applied.
     */
    private void HeapSort(MyADTInterface myAdt) {
        MyLogger.writeMessage("HeapSorting: " + myAdt.getClass().getSimpleName(),
                MyLogger.DebugLevel.INFO);
        int N = myAdt.size();

        for (int i = N / 2 - 1; i >= 0; i--)
            Heapify(myAdt, N, i);


        for (int i = N - 1; i >= 0; i--) {
            //SWAP
            int temp = myAdt.getMyADT().get(0);
            myAdt.getMyADT().set(0, myAdt.getMyADT().get(i));
            myAdt.getMyADT().set(i, temp);

            //Call max heapify on the reduced heap
            Heapify(myAdt, i, 0);
        }

    }


    /**
     * Helper to HeapSort method
     *
     * @param myAdt Reference to the ADT on which Heapify is applied.
     * @param n
     * @param i
     */
    private void Heapify(MyADTInterface myAdt, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root
        if (left < n && myAdt.getMyADT().get(left) > myAdt.getMyADT().get(largest))
            largest = left;

        // If right child is larger than largest so far
        if (right < n && myAdt.getMyADT().get(right) > myAdt.getMyADT().get(largest))
            largest = right;

        // If largest is not root
        if (largest != i) {
            int swap = myAdt.getMyADT().get(i);
            myAdt.getMyADT().set(i, myAdt.getMyADT().get(largest));
            myAdt.getMyADT().set(largest, swap);

            //Recursively heapify
            Heapify(myAdt, n, largest);
        }
    }
}
