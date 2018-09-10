package util;

import myTree.ActionsEnum;
import myTree.Node;
import java.util.ArrayList;

/**
 * TreeBuilder class to create a Tree of Nodes and also generate
 * backup copies i.e. Observer Trees which are exact replica of the
 * Main tree.
 * @author - Nitin Mahadik
 */

public class TreeBuilder {
    private Node mainRoot, backup1_root, backup2_root, backup_1, backup_2;

    /**
     * Default constructor, initialises private members
     */
    public TreeBuilder() {
        mainRoot = null;
        backup_1 = null;
        backup_2 = null;
        MyLogger.writeMessage("TreeBuilder constructor..", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    /**
     * Getter for the root of the Main Subject tree
     * @return the root node of the Subject Tree.
     */
    public Node getMainRoot() {
        return mainRoot;
    }


    /**
     * Getter for the root of the Observer 1 tree
     * @return the root node of the Observer 1 (backup1) Tree
     */
    public Node getBackup1_root() {
        return backup1_root;
    }


    /**
     * Getter for the root of the Observer 2 tree
     * @return the root node of the Observer 2 (backup2) Tree
     */
    public Node getBackup2_root() {
        return backup2_root;
    }


    /**
     * Searches the tree for a bNumber
     * @param bNumberIn - Input param to be searched in the tree.
     * @return - The Node in which the bNumber is found and
     * return null if not found
     */
    private Node searchByBNumber(int bNumberIn) {
        MyLogger.writeMessage("\n\nSearching for the bNumber: " + bNumberIn, MyLogger.DebugLevel.INFO);

        if (mainRoot != null)
            return searchByBNumber(mainRoot, bNumberIn);
        else
            return null;
    }

    /**
     * Recursive helper for the searchByBNumber(int) method.
     * @param nodeIn - Input node on which the traversal starts
     * @param bNumberIn - The bNumber to be searched
     * @return - The Node which has the bNumber.
     */
    private Node searchByBNumber(Node nodeIn, int bNumberIn) {
        Node temp = null;

        if (nodeIn != null) {
            int nodeInBNum = nodeIn.getbNumber();
            if (bNumberIn > nodeInBNum)
                temp = searchByBNumber(nodeIn.getRightNode(), bNumberIn);
            else if (bNumberIn < nodeInBNum)
                temp = searchByBNumber(nodeIn.getLeftNode(), bNumberIn);
            else
                temp = nodeIn;
        }

        return temp;
    }


    /**
     * Inserts/Updates the bNumber and coursename data to the tree
     * @param bNumberIn - the bNumber which needs
     *                  to be inserted or updated.
     * @param courseNameIn - the courseName to be inserted
     *                     for the mentioned bNumber
     */
    public void insert(int bNumberIn, String courseNameIn){
        if((bNumberIn <= 9999) && (courseNameIn.charAt(0) >= 'A' && courseNameIn.charAt(0) <= 'K')){
            Node temp = searchByBNumber(bNumberIn);

            if(temp == null) {
                mainRoot = insertHelper(mainRoot, bNumberIn, courseNameIn);
                if(backup_1 != null){
                    backup1_root = insertInObserver(backup_1, backup1_root, bNumberIn);
                    backup2_root = insertInObserver(backup_2, backup2_root, bNumberIn);
                }
            }
            else{
                ArrayList<String> tempArrayList = temp.getCourseNames();
                if(!tempArrayList.contains(courseNameIn)){
                    tempArrayList.add(courseNameIn);
                    temp.setCourseNames(tempArrayList);
                    temp.notifyAll(ActionsEnum.insert, courseNameIn);
                }
            }
        }
    }

    /**
     * Recursive Helper function to the insert(bNumber, courseName) method
     * @param nodeIn - Input node
     * @param bNumberIn Input bNumber that needs to be inserted/updated
     * @param courseNameIn Input courseName that needs to be inserted
     * @return The Root Node after successful insertion/updation
     */
    private Node insertHelper(Node nodeIn, int bNumberIn, String courseNameIn){

        if(nodeIn == null){ //tree is empty or traversal till leaf
            nodeIn = new Node(bNumberIn, courseNameIn);
            nodeIn.registerObserver(bNumberIn, courseNameIn);
            backup_1 = nodeIn.getBackup_Node_1_Ref();
            backup_2 = nodeIn.getBackup_Node_2_Ref();
            MyLogger.writeMessage("New Node inserted for the bNumber: " + bNumberIn + " and cloned to make observers",
                    MyLogger.DebugLevel.INFO);
        }
        else{
            if(bNumberIn > nodeIn.getbNumber())
                nodeIn.setRightNode(insertHelper(nodeIn.getRightNode(), bNumberIn, courseNameIn));
            else if(bNumberIn < nodeIn.getbNumber())
                nodeIn.setLeftNode(insertHelper(nodeIn.getLeftNode(), bNumberIn, courseNameIn));
        }


        return nodeIn;
    }

    /**
     * After inserting the new Node in the Subject Tree
     * Nodes are inserted in the Backup trees
     * @param backup - backup1 or 2 tree reference
     * @param backupRoot - root of the backup1 or 2 tree
     * @param bNumberIn - bNumber recently inserted in the Subject.
     * @return Root Node of the backup1 or 2 depending on what came in
     */
    public Node insertInObserver(Node backup, Node backupRoot, int bNumberIn){

        if(backupRoot == null){
            backupRoot = backup;
            MyLogger.writeMessage("Insertion in the backup trees", MyLogger.DebugLevel.INFO);
        }
        else {
            if (bNumberIn > backupRoot.getbNumber())
                backupRoot.setRightNode(insertInObserver(backup, backupRoot.getRightNode(), bNumberIn));
            else if(bNumberIn < backupRoot.getbNumber())
                backupRoot.setLeftNode(insertInObserver(backup, backupRoot.getLeftNode(), bNumberIn));
        }

        return backupRoot;
    }

    /**
     * Delete method to delete the courseNames from the student
     * bNumber Node and also update the observers.
     * @param bNumberIn - bNumber from which the courses
     *                  are to be removed.
     * @param courseNameIn - courseName which needs to be removed.
     */
    public void delete(int bNumberIn, String courseNameIn){
        MyLogger.writeMessage("Deletion in progress for the bNumber: " + bNumberIn + " and courseName: "  +courseNameIn
                , MyLogger.DebugLevel.INFO);
        Node temp = searchByBNumber(bNumberIn);

        if(temp != null){
            ArrayList<String> tempArrayList = temp.getCourseNames();
            if(tempArrayList.contains(courseNameIn)){
                tempArrayList.remove(courseNameIn);
                temp.setCourseNames(tempArrayList);
                temp.notifyAll(ActionsEnum.delete, courseNameIn);
            }
        }
    }


    /**
     * Inorder traversal (to dispaly in increasing order of bNumber)
     * and adding the Node data to the results file.
     * @param results - Reference to the results object passed
     *                from the driver class
     * @param nodeIn - First call has the root node of the Subject
     *               or Backup1 or Backup2 tree
     */
    public void printNodes(Results results, Node nodeIn){

        if(nodeIn == null)
            return;

        //Traverse inorder to get data sorted on
        //increasing order of bNumber
        printNodes(results, nodeIn.getLeftNode());

        results.storeNewResult(nodeIn.toString());

        printNodes(results, nodeIn.getRightNode());
    }
}