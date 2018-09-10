package treeForStrings;

import util.MyLogger;
import util.Results;
import visitor.Visitor;
import java.util.TreeMap;

/**
 * WordTreeMap Class
 *
 * @author Nitin Mahadik
 */
public class WordTreeMap implements WordTreeInterface {

    private TreeMap<String, Integer> myWordMap;
    private Results results;

    /**
     * Default contructor
     *
     * @param resultsIn Common results instance
     */
    public WordTreeMap(Results resultsIn) {
        MyLogger.writeMessage("WordTreeMap Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        myWordMap = new TreeMap<>();
        results = resultsIn;
    }

    /**
     * Getter for the WordTreeMap reference
     *
     * @return the reference to WordTreeMap
     */
    public TreeMap<String, Integer> getMyWordTree() {
        return myWordMap;
    }

    /**
     * Inserting data into the WordTreeMap
     *
     * @param word Value to be inserted in the WordTreeMap
     */
    public void addElement(String word) {

        if(myWordMap.get(word) == null){
            myWordMap.put(word, 1);
        }
        else{
            int count = myWordMap.get(word);
            myWordMap.put(word, ++count);
        }
    }

    /**
     * @return The size of the WordTreeMap
     */
    public int size() {
        return myWordMap.size();
    }


    /**
     * Used to call the visit method on the visitor
     *
     * @param visitor Reference to the visitor
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
