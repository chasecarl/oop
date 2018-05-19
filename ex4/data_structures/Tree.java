package oop.ex4.data_structures;

public class Tree {

    /** The root node of a tree */
    TreeNode root;

    /** Represents a single node of a tree */
    private class TreeNode {

        /** The left child of a tree */
        private TreeNode left;
        /** The right child of a tree */
        private TreeNode right;
        /** The value that this node stores */
        private int value;

        /** Constructs a TreeNode given its integer value */
        private TreeNode(int value) { this.value = value; }

        /** Constructs a TreeNode with the default value of 0 */
        private TreeNode() { this(0); }

    }
}
