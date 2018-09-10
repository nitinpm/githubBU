package myTree;

/**
 * Subject interface to notify all the observers.
 * @author - Nitin Mahadik
 */
public interface SubjectI{
	void notifyAll(ActionsEnum action, String courseNameIn);
}