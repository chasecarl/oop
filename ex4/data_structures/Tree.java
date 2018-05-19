package oop.ex4.data_structures;

public class Tree {

    /** The root node of a tree */
    TreeNode root;
    private int height;
    /** Represents a single node of a tree */
    private class TreeNode {

        /** The left child of a tree */
        private TreeNode left;
        /** The right child of a tree */
        private TreeNode right;
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
    }


}
