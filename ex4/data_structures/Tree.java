package oop.ex4.data_structures;

public class Tree {

    TreeNode root;

    private class TreeNode {

        private TreeNode left;
        private TreeNode right;
        private int value;

        private TreeNode(int value) { this.value = value; }

        private TreeNode() { this(0); }

    }

    public Tree() {
        this.root = new TreeNode();
    }
}
