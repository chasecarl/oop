package oop.ex4.data_structures;

public class AvlTree extends Tree {

    /** Stores a Fibonacci number */
    private static final double FIBONACCI = 0.5 * (1 + Math.sqrt(5));

    /**
     * @param node a no
     * @return the height of the node
     */
    private int getHeight(TreeNode node) {
        if (node != null) {
            return node.height;
        } else {
            return -1;
        }
    }

    /**
     * A method that calculates the maximum number of nodes in an AVL tree of height h
     * @param h height of the tree (a non-negative number)
     * @return maximum number of nodes in an AVL tree of height h
     */
    public static int findMinNodes(int h) {
        return (int)((Math.pow(FIBONACCI, h + 3) - Math.pow(-FIBONACCI, -(h + 3))) / (2 * FIBONACCI - 1)) - 1;
    }

    /**
     * Add a new node with key newValue into the tree
     * @param newValue new value to add to the tree
     * @return false iff newValue already exists in the tree
     */
    public boolean add(int newValue) {
        class AvlFunction extends Function {
            public void doAll(TreeNode current, TreeNode next) {
                super.doAll(current, next);
                correction(current);
            }
        }
        return addHelper(newValue, new AvlFunction()) != null;
    }

    /**
     * Correct the height of the node during balancing the tree.
     * @param node current node
     */
    private void heightCorrection(TreeNode node){
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight > rightHeight){
            node.height = leftHeight + 1;
        } else {
            node.height = rightHeight + 1;
        }
    }

    /**
     * Rotate the sub tree to the right to balance the tree
     * @param node current node
     * @return node after rotation
     */
    private TreeNode rightRotation(TreeNode node){
        TreeNode left = node.left;
        node.left = left.right;
        if (left.right != null) { left.right.parent = node; }
        left.parent = node.parent;
        if (node.parent == null)  { root = left; }
        else if (node == node.parent.left) { node.parent.left = left; }
        else { node.parent.right = left; }
        left.right = node;
        node.parent = left;
        return node;
    }

    /**
     * Rotate the sub tree to the left to balance the tree
     * @param node current node
     * @return node after rotation
     */
    private TreeNode leftRotation(TreeNode node){
        TreeNode right = node.right;
        node.right = right.left;
        if (right.left != null) { right.left.parent = node; }
        right.parent = node.parent;
        if (node.parent == null) { root = right; }
        else if (node == node.parent.left)  { node.parent.left = right; }
        else { node.parent.right = right; }
        right.left = node;
        node.parent = right;
        return node;
    }

    /**
     * @param node current node
     * @return the difference between height of the left and the right sub trees
     */
    private int balanceFactor(TreeNode node){
        return getHeight(node.right) - getHeight(node.left);
    }

    /**
     * Balance the tree after deletion of addition of the element.
     * @param node
     * @return
     */
    private TreeNode correction(TreeNode node) {
        if (node == null) return null;
//        heightCorrection(node);
        if (balanceFactor(node) == 2){
            if (balanceFactor(node.right) < 0){
                node.right = rightRotation(node.right);
            }
            return leftRotation(node);
        } else {
            if (balanceFactor(node) == -2){
                if (balanceFactor(node.left) > 0){
                    node.left = leftRotation(node.left);
                }
                return rightRotation(node);
            }
        }
        return node;
    }

//    public boolean add(int newValue){
//        TreeNode current = addHelper(newValue);
//        if (current == null) { return false; }
//        do {
//            correction(current);
//            current = current.parent;
//        } while (current != null);
//        return true;
//    }

    /**
     * Remove a node from the tree, if it exists.
     * @param toDelete value to delete
     * @return true iff toDelete found and deleted
     */
    public boolean delete(int toDelete){
        TreeNode node = searching(this.root, toDelete);
        if (node != null){
            deleteHelper(toDelete, node);
            correction(deleteHelper(toDelete, root));
            heightCheck(root);
            size--;
            return true;
        } else {
            return false;
        }
    }

    /** A default constructor. */
    public AvlTree() { super(); }

    /** A constructor that builds the tree by adding the elements in the input array one-by-one
     *  If the same values appears twice (or more) in the list, it is ignored.
     * @param data - values to add to tree
     */
    public AvlTree(int[] data) { super(data); }

    /** A copy constructor that builds the tree from existing tree.
     * @param tree - tree to be copied
     */
    public AvlTree(AvlTree tree) { super(tree); }


}
