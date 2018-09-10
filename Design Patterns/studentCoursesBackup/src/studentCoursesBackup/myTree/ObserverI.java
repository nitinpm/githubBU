package myTree;

/**
 * Observer interface to keep the backup copies updated.
 * @author - Nitin Mahadik
 */
public interface ObserverI{
	/**
	 * Update method to be implemented by the Observers
	 * to get updates from the Subject
	 * @param action - Can be insert or delete actions
	 * @param courseNameIn - This can be inserted or deleted by the
	 *                     Observers depending on the action param
	 */
	void update(ActionsEnum action, String courseNameIn);
}