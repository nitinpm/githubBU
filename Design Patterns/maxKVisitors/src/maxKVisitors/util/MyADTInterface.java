package util;

import visitor.Visitor;

import java.util.List;

/**
 * ADT interface
 *
 * @author Nitin Mahadik
 */
public interface MyADTInterface {

    /**
     * Returns the ADT reference
     *
     * @return Returns the reference to the ADT
     */
    List<Integer> getMyADT();


    /**
     * Adds a new element to the ADT
     *
     * @param value Value to be inserted in the ADT
     */
    void addElement(int value);


    /**
     * Gives the size of the ADT
     *
     * @return The size of the ADT
     */
    int size();


    /**
     * Displays the top kValue elements in the ADT
     *
     * @param kValue The count of top elements to be displayed.
     */
    void display(int kValue);


    /**
     * Used to call the visit method on the visitor
     *
     * @param visitor Reference to the visitor
     */
    void accept(Visitor visitor);
}
