package visitor;

import util.MyADTInterface;

/**
 * Visitor Interface
 *
 * @author Nitin Mahadik
 */
public interface Visitor {

    /**
     * Visit method for the visitors
     *
     * @param myAdt Reference to the ADT being visited.
     */
    void visit(MyADTInterface myAdt);
}
