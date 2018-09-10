package util;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * MyArray ADT Class
 *
 * @author Nitin Mahadik
 */
public class MyArray implements MyADTInterface {

    private List<Integer> myArrayList;
    private Results results;

    /**
     * Default contructor
     *
     * @param resultsIn Common results instance
     */
    public MyArray(Results resultsIn) {
        MyLogger.writeMessage("MyArray Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        myArrayList = new ArrayList<>();
        results = resultsIn;
    }

    /**
     * Getter for the myArrayList reference
     *
     * @return the reference to myArrayList
     */
    public List<Integer> getMyADT() {
        return myArrayList;
    }

    /**
     * Inserting data into the ADT
     *
     * @param value Value to be inserted in the ADT
     */
    public void addElement(int value) {
        MyLogger.writeMessage("MyArray->addingElement: " + value, MyLogger.DebugLevel.INFO);
        myArrayList.add(value);
    }

    /**
     * @return The size of the ADT
     */
    public int size() {
        return myArrayList.size();
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

        if (kValueIn > myArrayList.size())
            kValueIn = myArrayList.size();

        results.writeToStdout("\nOUTPUT -------> Top " + kValueIn + " elements of " +
                this.getClass().getSimpleName() + ":\n");

        for (int i = myArrayList.size() - 1; ; i--) {
            results.writeToStdout(myArrayList.get(i).toString());
            count++;
            if (count == kValueIn)
                break;
        }
        System.out.println("");
    }
}
