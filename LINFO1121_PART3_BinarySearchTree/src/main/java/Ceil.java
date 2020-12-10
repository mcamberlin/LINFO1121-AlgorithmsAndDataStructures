
public class Ceil
{
	/**
	 * Find in the tree the smallest element greater than or equal to value
	 * (so either the element itself or the element located directly after it
	 * in order of magnitude). If such an element does not exist,
	 * it must return null.
	 */
	public static Integer ceil2(Node root, int value)
	{
		return ceil(root, value, null);
	}

	public static Integer ceil(Node root, int value, Node min)
	{
		if (root == null)
		{
			if (min == null)
				return null;
			else
				return min.getValue();
		}

		else if (root.getValue() < value)
			return ceil(root.getRight(), value, min);

		else if (root.getValue() > value)
			return ceil(root.getLeft(), value, root);

		else // if(root.getValue() == value)
			return value;
	}

	public static Integer ceil(Node root, int value)
	{
		Node min = null;
		while(root != null)
		{
			if (root.getValue() < value)
				root = root.getRight();

			else if (root.getValue() > value)
			{
				min = root;
				root = root.getLeft();

			}

			else // if(root.getValue() == value)
				 return value;
		}
		if (min == null)
			return null;
		else
			return min.getValue();

	}


}

