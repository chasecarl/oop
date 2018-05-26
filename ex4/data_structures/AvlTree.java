package oop.ex4.data_structures;

public class AvlTree extends Tree {

    /** Stores the Fibonacci ratio */
    private static final double FIBONACCI = 0.5 * (1 + Math.sqrt(5));
    /** Used in fixHeight method to change its behavior to match the case when there is only one rotation */
    private static final boolean ONE_STEP_ROTATION = true;
    /** Used in fixHeight method to change its behavior to match the case when there are two rotations */
    private static final boolean TWO_STEP_ROTATION = false;

    /**
     * @param node the node that we want to know its height
     * @return the height of the node if it isn't null, -1 otherwise
     */
    private int getHeight(TreeNode node) {
        if (node != null) { return node.height; }
        return -1;
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
            public void fix(TreeNode current, TreeNode next) {
                super.fix(current, next);
                correction(current);
            }
        }
        return addHelper(newValue, new AvlFunction()) != null;
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
     * Balance the tree after deletion or addition of the element.
     * @param node a node to balance
     * @return the same node
     */
    private TreeNode correction(TreeNode node) {
        if (node == null) return null;
        int balanceFactor = balanceFactor(node);
        if (balanceFactor == 2){
            if (balanceFactor(node.right) < 0){
                rightRotation(node.right);
                fixHeight(node, RIGHT, TWO_STEP_ROTATION);
            }
            return fixHeight(leftRotation(node));
        }
        else if (balanceFactor == -2) {
            if (balanceFactor(node.left) > 0){
                leftRotation(node.left);
                fixHeight(node, LEFT, TWO_STEP_ROTATION);
            }
            return fixHeight(rightRotation(node));
        }
        return node;
    }

    /**
     * Fixes height of the given node (or its children in case of two step rotation) after rotation
     * @param node a node to fix
     * @param right in the case of two step rotation defines whether we need to fix right or left children
     * @param oneStepRotation defines the kind of a rotation (two or one step)
     * @return the fixed node
     */
    private TreeNode fixHeight(TreeNode node, boolean right, boolean oneStepRotation) {
        if (oneStepRotation) {
            node.height -= 2;
            return node;
        }
        if (right) {
            node.right.height++;
            node.right.right.height--;
        }
        else {
            node.left.height++;
            node.left.left.height--;
        }
        return node;
    }

    /**
     * An overloaded version that is called in case of one step rotation;
     * here we also don't need to specify the right parameter as we don't need it in this case
     * @param node a node to fix
     * @return the fixed node
     */
    private TreeNode fixHeight(TreeNode node) {
        return fixHeight(node, RIGHT, ONE_STEP_ROTATION);
    }

    /**
     * Remove a node from the tree, if it exists.
     * @param toDelete value to delete
     * @return true iff toDelete found and deleted
     */
    public boolean delete(int toDelete){
        TreeNode node = searching(this.root, toDelete);
        if (node != null) {
            deleteHelper(toDelete, node);
            correction(deleteHelper(toDelete, root));
            heightCheck(root);
            size--;
            return true;
        }
        return false;
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
