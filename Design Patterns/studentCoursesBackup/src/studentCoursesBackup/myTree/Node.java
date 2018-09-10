package myTree;

import util.MyLogger;
import java.util.ArrayList;

/**
 * Node class: Creates Nodes (Subject and Observers) and
 * stores left and right pointers to other nodes.
 * @author - Nitin Mahadik
 */
public class Node implements ObserverI, SubjectI, Cloneable{

    private int bNumber;
    private ArrayList<String> courseNames;
    private ObserverI backup_Node_1_Ref, backup_Node_2_Ref;
    private Node rightNode, leftNode;

    /**
     * Constructor to create the Subject node.
     * @param bNumberIn - Initial bNumber during Node creation
     * @param courseNameIn - during Node creation.
     */
    public Node(int bNumberIn, String courseNameIn){
        bNumber = bNumberIn;
        courseNames = new ArrayList<>();
        rightNode = null;
        leftNode = null;
        courseNames.add(courseNameIn);
        MyLogger.writeMessage("Node with bNumber: " + bNumberIn + " created.", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    /**
     * Get the bNumber of the current Node in focus
     * @return the bNumber of the current referred Node
     */
    public int getbNumber() {
        return bNumber;
    }

    /**
     * Gets the reference to the list of courses on the current Node
     * @return the ArrayList of courses for the current referred Node
     */
    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    /**
     * Gets the right node reference
     * @return the Right Node linked to the current referred Node
     */
    public Node getRightNode() {
        return rightNode;
    }

    /**
     * Used to set a new node to the right of the current Node
     * @param rightNode - This node is set to the right side
     *                  of the current referred Node
     */
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * Gets the left node reference
     * @return the Left Node linked to the current referred Node
     */
    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * Used to set a new node to the right of the current Node
     * @param leftNode - This node is set to the right side
     *                  of the current referred Node
     */
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Setter for the courseNames private member.
     * @param courseNames Takes a reference of an ArrayList and
     *                    sets it to the current Node's
     *                    courseNames list.
     */
    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames = courseNames;
    }

    /**
     * Getter for a reference to the backup node 1
     * @return the reference to the backup node 1
     */
    public Node getBackup_Node_1_Ref() {
        return (Node)backup_Node_1_Ref;
    }

    /**
     * Getter for a reference to the backup node 2
     * @return the reference to the backup node 2
     */
    public Node getBackup_Node_2_Ref() {
        return (Node)backup_Node_2_Ref;
    }

    /**
     * Used to register the backup Node observers
     * @param bNumberIn The bNumber of the Subject Node which is used
     *                  to clone and create observer copies (backup)
     * @param courseNameIn CourseNames of the Subject is also cloned
     *                     to the observer copies (backup)
     */
    public void registerObserver(int bNumberIn, String courseNameIn){
        backup_Node_1_Ref = clone(bNumberIn, courseNameIn);
        backup_Node_2_Ref = clone(bNumberIn, courseNameIn);
        MyLogger.writeMessage("Backup Nodes created for the bNumber: " + bNumberIn, MyLogger.DebugLevel.INFO);
    }


    /**
     * Clones the subject node into observer nodes.
     * @param bNumberIn - of the subject node
     * @param courseNameIn - default courseName during creation
     * @return clone of the subject Node
     */
    private Node clone(int bNumberIn, String courseNameIn){
        return new Node(bNumberIn, courseNameIn);
    }


    /**
     * Pretty display of bNumber and courses
     * @return - string containing above info
     */
    @Override
    public String toString(){
        String nodeDetails = "";

        for(int i = 0; i < courseNames.size(); i++)
            nodeDetails += " " + courseNames.get(i);

        return bNumber + ": " + nodeDetails;
    }


    /**
     * Method of SubjectI interface
     * Notifies and updates all the observers of the change
     * @param actionIn - insert or delete of the courseNameIn
     * @param courseNameIn - The course to be inserted/deleted
     */
    public void notifyAll(ActionsEnum actionIn, String courseNameIn){
        backup_Node_1_Ref.update(actionIn, courseNameIn);
        backup_Node_2_Ref.update(actionIn, courseNameIn);
        MyLogger.writeMessage("Backup trees are notified of the update: " + actionIn.toString() + " for the courseName: " + courseNameIn,
                MyLogger.DebugLevel.INFO);
    }

    /**
     * Update method from the ObserverI interface
     * updates the notified changes from the Subject
     * @param actionIn - Action to be performed during update
     * @param courseNameIn - This param needs to be inserted/deleted
     *                     depending on the action param
     */
    public void update(ActionsEnum actionIn, String courseNameIn){
        ArrayList<String> temp = this.getCourseNames();

        switch(actionIn){
            case insert:
                if(!temp.contains(courseNameIn)){
                    temp.add(courseNameIn);
                    this.setCourseNames(temp);
                }
                break;
            case delete:
                if(temp.contains(courseNameIn)){
                    temp.remove(courseNameIn);
                    this.setCourseNames(temp);
                }
        }
    }
}