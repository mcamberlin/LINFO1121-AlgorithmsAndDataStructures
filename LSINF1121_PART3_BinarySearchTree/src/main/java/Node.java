class Node {
	private Node left, right;
	private int value;

	public Node(int value) {
		this.left = null;
		this.right = null;
		this.value = value;
	}

    public Node() {
    }

    /**
      * @return the value contained in this node
      */
    public int getValue() {
    	return value;
    }

    /**
     * @return the node on the left (whose value is < than the current value)
     * if it exists, null if not
     */
    public Node getLeft() {
    	return left;
    }

    /**
      * @return the node on the right (whose value is > than the current value)
      * if it exists, null if not
      */
    public Node getRight() {
    	return right;
    }

    /**
		 * Add a new value newVal in the BST
     */
    public void add(int newVal) {
    	if(newVal < value && left == null) left = new Node(newVal);
        else if(newVal < value) left.add(newVal);
        else if(newVal > value && right == null) right = new Node(newVal);
        else if(newVal > value) right.add(newVal);
    }
}
