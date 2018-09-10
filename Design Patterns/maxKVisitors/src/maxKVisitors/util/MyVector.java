package util;

import visitor.Visitor;

import java.util.List;
import java.util.Vector;

/**
 * MyVector ADT Class
 *
 * @author Nitin Mahadik
 */
public class MyVector implements MyADTInterface {

    private List<Integer> myVector;
    private Results results;

    /**
     * Default contructor
     *
     * @param resultsIn Common results instance
     */
    public MyVector(Results resultsIn) {
        MyLogger.writeMessage("MyVector Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        myVector = new Vector<>();
        results = resultsIn;
    }

    /**
     * Getter for the myVector reference
     *
     * @return the reference to myVector
     */
    public List<Integer> getMyADT() {
        return myVector;
    }

    /**
     * Inserting data into the ADT
     *
     * @param value Value to be inserted in the ADT
     */
    public void addElement(int value) {
        MyLogger.writeMessage("MyVector->addingElement: " + value, MyLogger.DebugLevel.INFO);
        myVector.add(value);
    }


    /**
     * @return The size of the ADT
     */
    public int size() {
        return myVector.size();
    }


    /**
     * Used to call the visit method on the visitor
     *
     * @param visitor Reference to the visitor
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    /**
     * Displays the top kValue elements in the ADT
     *
     * @param kValueIn The count of top elements to be displayed.
     */
    public void display(int kValueIn) {
        int count = 0;

        if (kValueIn > myVector.size())
            kValueIn = myVector.size();

        results.writeToStdout("\nOUTPUT -------> Top " + kValueIn + " elements of " +
                this.getClass().getSimpleName() + ":\n");

        for (int i = myVector.size() - 1; ; i--) {
            results.writeToStdout(myVector.get(i).toString());
            count++;
            if (count == kValueIn)
                break;
        }
        System.out.println();
    }
}
