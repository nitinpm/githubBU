package visitor;

import treeForStrings.WordTreeInterface;

/**
 * Visitor Interface
 *
 * @author Nitin Mahadik and Aditya Tyagi
 */
public interface Visitor {

    /**
     * Visit method for the visitors
     *
     * @param myWordMap Reference to the WordMap being visited.
     */
    void visit(WordTreeInterface myWordMap);
}
