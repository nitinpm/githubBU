package visitor;

import util.Results;
import treeForStrings.WordTreeInterface;
import java.util.Map;

/**
 * Second Module class to  counts
 * the number of words,
 * number of unique distinct words,
 * and number of characters in the tree and
 * stores it in a file named output.txt
 * @author Nitin Mahadik
 */
public class CountVisitor implements Visitor {

    private Results results;

    /**
     * Parameterized constructor
     * @param resultsIn results reference to store new result in it.
     */
    public CountVisitor(Results resultsIn){
        results = resultsIn;
    }


    /**
     * Visit method to traverse and get the count of words
     * Distinct words and characters.
     *
     * @param myWordMap Reference to the WordMap being visited.
     */
    public void visit(WordTreeInterface myWordMap){
        int wordCount = 0, distinctWordCount, charCount = 0;

        distinctWordCount = myWordMap.size();

        for(Map.Entry<String,Integer> entry : myWordMap.getMyWordTree().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            wordCount = wordCount + value;
            charCount = charCount + value*key.length();
        }

        results.storeNewResult("Word Count: " + wordCount +
                "\tDistinct Words: " + distinctWordCount +
                "\tCharacter Count: " + charCount);
    }
}
