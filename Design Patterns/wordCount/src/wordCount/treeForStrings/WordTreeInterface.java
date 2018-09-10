package treeForStrings;

import visitor.Visitor;
import java.util.TreeMap;

/**
 * WordTreeMap interface
 *
 * @author Nitin Mahadik
 */
public interface WordTreeInterface {

    /**
     * Returns the WordTreeMap reference
     *
     * @return Returns the reference to the WordTreeMap
     */
    TreeMap<String, Integer> getMyWordTree();


    /**
     * Adds a new word to the WordTreeMap
     *
     * @param word Value to be inserted in the WordTreeMap
     */
    void addElement(String word);


    /**
     * Gives the size of the WordTreeMap
     *
     * @return The size of the WordTreeMap
     */
    int size();



    /**
     * Used to call the visit method on the visitor
     *
     * @param visitor Reference to the visitor
     */
    void accept(Visitor visitor);
}
