package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tree {

    /** Used in addHelper method to change its behavior to the left part of the tree */
    private static final boolean LEFT = false;
    /** Used in addHelper method to change its behavior to the right part of the tree */
    private static final boolean RIGHT = true;

    /** The root node of a tree */
    TreeNode root;
    /** Represents number of nodes in the tree */
    protected int size;

    /** Represents a single node of a tree */
    protected class TreeNode {

        /** The left child of a node */
        protected TreeNode left;
        /** The right child of a node */
        protected TreeNode right;
        /** The parent of a node */
        protected TreeNode parent;
        /** The value that this node stores */
        protected int value;
        /** The height of this node */
        protected int height;

        /** Constructs a TreeNode given its integer value */
        private TreeNode(int value) {
            this.value = value;
            this.height = 0;
        }

        /** Constructs a TreeNode with the default value of 0 */
        private TreeNode() { this(0); }

        /** Constructs a TreeNode given its integer value and its parent node */
        private TreeNode(int value, TreeNode parent) {
            this(value);
            this.parent = parent;
        }

        /** Constructs a TreeNode given its integer value, its parent node,
         * and a switch that represents whether the new node is a right or a left child */
        private TreeNode(int value, TreeNode parent, boolean right) {
            this(value, parent);
            if (parent == null) return;
            if (right) { parent.right = this; }
            else { parent.left = this; }
        }
    }

    /** Wraps creating a new node with incrementing size */
    private TreeNode getNewTreeNode(int newValue, TreeNode parent, boolean right) {
        size++;
        return new TreeNode(newValue, parent, right);
    }

    private TreeNode getNewTreeNode(int newValue) { return getNewTreeNode(newValue, null, RIGHT); }

    /** @return the height of the tree */
    private int height() { return root.height; }

    /*
    Return the height of the node.
     */
    //TODO May by it will be better to stay this function instead of height()???
    protected int getHeight(TreeNode node){
        if (node != null){
            return node.height;
        } else {
            return 0;
        }
    }

    /**
     * Add a new node with key newValue into the tree
     * @param newValue new value to add to the tree
     * @return false iff newValue already exists in the tree
     */
    public boolean add(int newValue) {
        return addHelper(root, root, RIGHT, newValue) != null;
    }

    private TreeNode addHelper(TreeNode current, TreeNode parent, boolean right, int newValue) {
        if (current == null) {
            if (parent == null) {
                root = getNewTreeNode(newValue);
                return root;
            }
            return getNewTreeNode(newValue, parent, right);
        }
        if (newValue == current.value) { return null; }
        if (newValue < current.value) { return go(LEFT, newValue, current); }
        else { return go(RIGHT, newValue, current); }
    }

    private TreeNode go(boolean right, int newValue, TreeNode current) {
        TreeNode next = getChild(current, right);
        TreeNode added = addHelper(next, current, right, newValue);
        // we need to reassign here because there can be old null link (as next stored value before adding a new node)
        next = getChild(current, right);
        if (added != null) {
            if (current.height <= next.height) { current.height++; }
            return added;
        }
        return null;
    }

    private TreeNode getChild(TreeNode node, boolean right) {
        // actually we don't need this line, but maybe we will use this method somewhere where node can be null
        if (node == null) return null;
        if (right) return node.right;
        else return node.left;
    }

    /** @return number of nodes in the tree */
    public int size() { return size; }

    /**
     * Returns an iterator for the Tree. The returned iterator iterates
     * over the tree nodes in an ascending order, and does NOT implement the remove() method.
     * @return an iterator for the Tree.
     */
    public java.util.Iterator<Integer> iterator() {
        class TreeIterator implements Iterator<Integer> {

            private TreeNode current;

            @Override
            public boolean hasNext() {
                if (current == null) { return true; }
                return successor(current) != null;
            }

            @Override
            public Integer next() {
                if (current == null) {
                    current = getMinTreeNode(root);
                    if (current == null) { throw new NoSuchElementException(); }
                }
                else { current = successor(current); }
                return current.value;
            }
        }
        return new TreeIterator();
    }

    /**
     * @param subTreeRoot the root of a subtree
     * @return the minimum node of a subtree
     */
    private TreeNode getMinTreeNode(TreeNode subTreeRoot) {
        if (subTreeRoot == null) { return null; }
        TreeNode current = subTreeRoot;
        while (current.left != null) { current = current.left; }
        return current;
    }

    /**
     * @param node a node regarding which a successor is searched
     * @return a successor node
     */
    private TreeNode successor(TreeNode node) {
        if (node == null) { return null; }
        if (node.right == null) {
            // it means that node is root
            if (node.parent == null) { return null; }
//            // it means that we're in a right subtree
//            else if (node.parent.value < node.value) { return null; }
//            // it means that we're in a left subtree
            else return getFirstRightTurn(node);
        }
        else return getMinTreeNode(node.right);
    }

    private TreeNode getFirstRightTurn(TreeNode node) {
        TreeNode parent = node.parent;
        while (parent != null && parent.value < node.value) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /** A default constructor */
    public Tree() { this.root = null; }

    /** A constructor that builds the tree by adding the elements in the input array one-by-one.
     * If the same values appears twice (or more) in the list, it is ignored.
     * @param data elements to put into the tree
     * */
    public Tree(int[] data) {
        for (int element : data) { this.add(element); }
    }

    /**
     * Does tree contain a given input value.
     * @param searchVal value to search for
     * @return if val is found in the tree, return the depth of its node (where 0 is the root).
     * Otherwise -- return -1.
     */
    public int contains(int searchVal){
        TreeNode node = searching(this.root, searchVal);
        if (node != null){
            if (searchVal > this.root.value) return 1 + this.root.right.height - node.height;
            if (searchVal < this.root.value) return 1 + this.root.left.height - node.height;
            return this.height() - node.height;
        }
        else {
            return -1;
        }
    }

    /*
    Recursive helper for searching a value in the tree.
     */
    protected TreeNode searching (TreeNode currentNode, int searchVal){
        if (currentNode == null){
            return null;
        }
        if (searchVal == currentNode.value){
            return currentNode;
        }
        if (searchVal > currentNode.value){
            return searching(currentNode.right, searchVal);
        } else {
            return searching(currentNode.left, searchVal);
        }
    }

    /**
     * Remove a node from the tree, if it exists.
     * @param toDelete value to delete
     * @return true iff toDelete found and deleted
     */
    public boolean delete(int toDelete){
        TreeNode node = searching(this.root, toDelete);
        if (node != null){
            deleteHelper(toDelete, root);
            heightCheck(root);
            size--;
            return true;
        } else {
            return false;
        }
    }

    protected TreeNode deleteHelper(int toDelete, TreeNode currentNode) {
        if (currentNode == null) {
            return currentNode;
        }
        if (currentNode.value > toDelete) {
            currentNode.left = deleteHelper(toDelete, currentNode.left);
        } else {
            if (currentNode.value < toDelete) {
                currentNode.right = deleteHelper(toDelete, currentNode.right);
            } else {
                if (currentNode.right != null && currentNode.left != null) {
                    //TODO After deletion of the element the height of the node doesn't changes, HOW TO SOLVE IT???
                    currentNode.value = minNode(currentNode.right).value;
                    currentNode.right = deleteHelper(currentNode.value, currentNode.right);
                } else {
                    if (currentNode.left != null) {
                        //TODO After deletion of the element the height of the node doesn't changes, HOW TO SOLVE IT???
                        currentNode = currentNode.left;
                    } else {
                        //TODO After deletion of the element the height of the node doesn't changes, HOW TO SOLVE IT???
                        currentNode = currentNode.right;
                    }
                }
            }
        }
        return currentNode;
    }

    protected int heightCheck(TreeNode node) {
        if (node.right != null && node.left != null) {
            node.height = 1 + Math.max(heightCheck(node.right), heightCheck(node.left));
        } else {
            if (node.right != null){
                node.height = 1 + heightCheck(node.right);
            } else {
                if (node.left != null){
                    node.height = 1 + heightCheck(node.left);
                } else {
                    node.height = 0;
                }
            }
        }
        return node.height;
    }

    private TreeNode minNode(TreeNode root){
        if (root.left == null){
            return root;
        } else {
            return minNode(root.left);
        }
    }

    private TreeNode removeMinNode(TreeNode node){
        if (node.left == null) return node;
        node.left = removeMinNode(node.left);
        node.height = getHeight(node.left) + getHeight(node.right) + 1;
        return node;
    }

    // TODO: THESE METHODS AREN'T REQUIRED AND NEED TO BE CUT OFF AT THE END
    private int[] toArray() {
        int[] result = new int[(int)Math.pow(2, height() + 1) - 1];

        traverseToArray(1, result, root);

        return result;
    }

    private void traverseToArray(int i, int[] result, TreeNode current) {
        if (current == null) { return; }
        result[i - 1] = current.value;
        traverseToArray(i * 2, result, current.left);
        traverseToArray(i * 2 + 1, result, current.right);
    }

    public String toString() {
        if (root == null) return "";

        int[] arrayTree = toArray();
        String result = "";
        int spaceNumber = height();

        String currentHeightString = "";
        String currentSpaces = "";
        for (int i = 0; i < arrayTree.length; i++) {
            if (isPowerOfTwo(i + 1)) {
                if (spaceNumber != height()) {
                    currentHeightString += currentSpaces + "\n";
                    result += currentHeightString;
                }
                currentSpaces = getNumberOfSpaces(spaceNumber);
                spaceNumber--;
                currentHeightString = currentSpaces;
            }
            String element;
            if (arrayTree[i] == 0) { element = " "; }
            else { element = Integer.toString(arrayTree[i]); }
            currentHeightString += element + " ";
        }
        result += currentHeightString.substring(0, currentHeightString.length() - 1);
        result += "\nsize: " + size();

        return result;
    }

    private String getNumberOfSpaces(int number) {
        String result = "";
        for (int i = 0; i < number; i++) { result += " "; }
        return result;
    }

    public boolean isPowerOfTwoWrapper(int i) { return isPowerOfTwo(i); }
    private boolean isPowerOfTwo(int i) {
        if (i == 1) return true;
        if (i % 2 == 0) return isPowerOfTwo(i / 2);
        return false;
    }
}
