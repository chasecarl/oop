package oop.ex4.data_structures;

public class AvlTree extends Tree {

    /** Represents a single node of a tree */
    private class TreeNode {

        /** The left child of a node */
        private AvlTree.TreeNode left;
        /** The right child of a node */
        private AvlTree.TreeNode right;
        /** The parent of a node */
        private AvlTree.TreeNode parent;
        /** The value that this node stores */
        private int value;
        /** The height of this node */
        private int height;

        /** Constructs a TreeNode given its integer value */
        private TreeNode(int value) {
            this.value = value;
            this.height = 0;
        }

        /** Constructs a TreeNode with the default value of 0 */
        private TreeNode() { this(0); }

        /** Constructs a TreeNode given its integer value and its parent node */
        private TreeNode(int value, AvlTree.TreeNode parent) {
            this(value);
            this.parent = parent;
        }

        /** Constructs a TreeNode given its integer value, its parent node,
         * and a switch that represents whether the new node is a right or a left child */
        private TreeNode(int value, AvlTree.TreeNode parent, boolean right) {
            this(value, parent);
            if (parent == null) return;
            if (right) { parent.right = this; }
            else { parent.left = this; }
        }

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

    private int getHeight(TreeNode node){
        if (node != null){
            return node.height;
        } else {
            return 0;
        }
    }

    private int balanceFactor(TreeNode node){
        return getHeight(node.right) - getHeight(node.left);
    }

    private TreeNode correction(TreeNode node){
        heightCorrection(node);
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
        super.add(newValue);
        //TODO To add correction of node's height
        //return correction(//TODO To add the node, which was changed, here)
         return true; //TODO To delete this line later
    }


}
