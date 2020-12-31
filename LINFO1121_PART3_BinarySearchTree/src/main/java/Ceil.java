public class Ceil {
	/**
	 * Find in the tree the smallest element greater than or equal to value
	 * (so either the element itself or the element located directly after it
	 * in order of magnitude). If such an element does not exist,
	 * it must return null.
	 */
	public static Integer ceil(Node root, int value)
	{
		Integer previousBeforeGoingLeft = null;
		while(root != null)
		{
			if(value<root.getValue())
			{
				previousBeforeGoingLeft = root.getValue();
				root = root.getLeft();
			}
			else if(value>root.getValue())
				root = root.getRight();
			else
				return value;
		}
		return previousBeforeGoingLeft;
	}

	public static Integer ceilRecursive(Node root, int value)
	{
		return ceilRecursive(root,value, null);
	}

	public static Integer ceilRecursive(Node root, int value, Integer previousBeforeGoingLeft)
	{
		if(root == null)
			return previousBeforeGoingLeft;

		if(value < root.getValue()) // go to the left subtree
		{
			previousBeforeGoingLeft = root.getValue();
			return ceilRecursive(root.getLeft(), value,previousBeforeGoingLeft);
		}
		else if(value > root.getValue()) // go to the right subtree
			return ceilRecursive(root.getRight(),value,previousBeforeGoingLeft);
		else
			return new Integer(value);
	}
}
