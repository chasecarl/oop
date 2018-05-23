package oop.ex4.data_structures;

public class AvlTree extends Tree {

    /**
     * A method that calculates the maximum number of nodes in an AVL tree of height h
     * @param h - height of the tree (a non-negative number).
     * @return maximum number of nodes of height h
     */
    public static int findMaxNodes(int h) {
        int result = 0;
        for (int i = 0; i <= h; i++) {
            result += Math.pow(2, i);
        }
        return result;
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
        TreeNode X = node;
        TreeNode Y = node.left;
        X.left = Y.right;
        Y.right = X;
        heightCorrection(X);
        heightCorrection(Y);
        return Y;
    }

    private TreeNode leftRotation(TreeNode node){
        TreeNode X = node.right;
        TreeNode Y = node;
        Y.right = X.left;
        X.left = Y;
        heightCorrection(Y);
        heightCorrection(X);
        return X;
    }


    private int balanceFactor(TreeNode node){
        return getHeight(node.right) - getHeight(node.left);
    }

    private TreeNode correction(TreeNode node) {
        if (node == null) return null;
        heightCheck(node);
        if (balanceFactor(node) == 2){
            if (balanceFactor(node.right) < 0){
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        } else {
            if (balanceFactor(node) == -2){
                if (balanceFactor(node.left) > 0){
                    node.left = leftRotation(node.left);
                    return rightRotation(node);
                }
            }
        }
        return node;
    }


    public boolean add(int newValue){
        return correction(addHelper(newValue)) != null;
    }

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
    public AvlTree() { super();}

    /** A constructor that builds the tree by adding the elements in the input array one-by-one
     *  If the same values appears twice (or more) in the list, it is ignored.
     * @param data - values to add to tree
     */
    public AvlTree(int[] data) { super(data);}
}
