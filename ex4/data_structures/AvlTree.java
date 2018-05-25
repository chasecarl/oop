package oop.ex4.data_structures;

public class AvlTree extends Tree {

    /** Stores a Fibonacci number */
    private static final double FIBONACCI = 0.5 * (1 + Math.sqrt(5));

    //TODO: IS THIS RIGHT?
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

    public boolean add(int newValue) {
        class AvlFunction extends Function {
            public void doAll(TreeNode current, TreeNode next) {
                super.doAll(current, next);
                correction(current);
            }
        }
        return addHelper(newValue, new AvlFunction()) != null;
    }

    private void heightCorrection(TreeNode node){
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight > rightHeight){
            node.height = leftHeight + 1;
        } else {
            node.height = rightHeight + 1;
        }
    }

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


    private int balanceFactor(TreeNode node){
        return getHeight(node.right) - getHeight(node.left);
    }

    private TreeNode correction(TreeNode node) {
        if (node == null) return null;
//        heightCorrection(node);
        if (balanceFactor(node) == 2){
            if (balanceFactor(node.right) < 0){
                rightRotation(node.right);
                node.right.height++;
                node.right.right.height--;
            }
            TreeNode result = leftRotation(node);
            result.height -= 2;
            return result;
        } else {
            if (balanceFactor(node) == -2){
                if (balanceFactor(node.left) > 0){
                    leftRotation(node.left);
                    node.left.height++;
                    node.left.left.height--;
                }
                TreeNode result = rightRotation(node);
                result.height -= 2;
                return result;
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
