package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tree {

    //------------------------------------------------------------------------------------------------
    //---------------------------------------- fields ------------------------------------------------
    //------------------------------------------------------------------------------------------------

    /** Used in addHelper method to change its behavior to the left part of the tree */
    static final boolean LEFT = false;
    /** Used in addHelper method to change its behavior to the right part of the tree */
    static final boolean RIGHT = true;
    /** Used in toArray method to change its behavior to copy the tree */
    private static boolean COPY = true;
    /** Used in toArray method to change its behavior to traverse the tree without copying */
    private static boolean TRAVERSE = false;

    /** The root node of a tree */
    TreeNode root;
    /** Represents number of nodes in the tree */
    int size;

    //------------------------------------------------------------------------------------------------
    //------------------------------------------ Node ------------------------------------------------
    //------------------------------------------------------------------------------------------------

    /** Represents a single node of a tree */
    class TreeNode {

        /** The left child of a node */
        TreeNode left;
        /** The right child of a node */
        TreeNode right;
        /** The parent of a node */
        TreeNode parent;
        /** The value that this node stores */
        int value;
        /** The height of this node */
        int height;

        /**
         * Constructs a TreeNode given its integer value
         * @param value value of the new node
         */
        private TreeNode(int value) {
            this.value = value;
            this.height = 0;
        }

        /**
         * Constructs a TreeNode given its integer value and its parent node
         * @param value value of the new node
         * @param parent parent node of the new node
         */
        private TreeNode(int value, TreeNode parent) {
            this(value);
            this.parent = parent;
        }

        /**
         * Constructs a TreeNode given its integer value, its parent node,
         * and a switch that represents whether the new node is a right or a left child
         * @param value value of the new node
         * @param parent parent node of the new node
         * @param right a switch that represents whether the new node is a right or a left child
         */
        private TreeNode(int value, TreeNode parent, boolean right) {
            this(value, parent);
            if (parent == null) return;
            if (right) { parent.right = this; }
            else { parent.left = this; }
        }
    }

    /**
     * Wraps creating a new node with incrementing size
     * @param newValue value of the new node
     * @param parent parent node of the new node
     * @param right a switch that represents whether the new node is a right or a left child
     * @return the new node
     */
    private TreeNode getNewTreeNode(int newValue, TreeNode parent, boolean right) {
        size++;
        return new TreeNode(newValue, parent, right);
    }

    /**
     * An overloaded version that takes only one argument and creates a node without parent
     * @param newValue value of the new node
     * @return the new node
     */
    private TreeNode getNewTreeNode(int newValue) { return getNewTreeNode(newValue, null, RIGHT); }

    /** An array modification of the getNewTreeNode method that creates a new node and puts it at
     * specified place in the given array, assigning to it the given height (used in the copy constructor)
     * @param newValue a value for a new node
     * @param height a height of the new node
     * @param array an array where the new node goes
     * @param i the new node's index in the array
     * @return the new node
     */
    private TreeNode getNewTreeNode(int newValue, int height, TreeNode[] array, int i) {
        int parentIndex = i == 1 ? 0 : i / 2 - 1;
        TreeNode newNode = getNewTreeNode(newValue, array[parentIndex], i % 2 == 1);
        newNode.height = height;
        array[i - 1] = newNode;
        return newNode;
    }

    //------------------------------------------------------------------------------------------------
    //------------------------------------- height method -------------------------------------------
    //------------------------------------------------------------------------------------------------

    /** @return the height of the tree */
    int height() { return root.height; }


    //------------------------------------------------------------------------------------------------
    //------------------------------------------ add -------------------------------------------------
    //------------------------------------------------------------------------------------------------

    /**
     * Add a new node with key newValue into the tree
     * @param newValue new value to add to the tree
     * @return false iff newValue already exists in the tree
     */
    public boolean add(int newValue) {
        return addHelper(newValue)!= null;
    }

    /**
     * A recursive method that traverses the tree given the new node value to find a place for it in the tree
     * and add the corresponding new node on it
     * @param current the node which we're looking at the current recursion level
     * @param parent the node from which the current recursion was called
     * @param right a boolean value that indicates whether we go to the left or to the right child
     * @param newValue a value to add
     * @param func a Function object whose method is used when going back from recursion
     * @return a newly added TreeNode; null iff there already a node with the newValue
     */
    TreeNode addHelper(TreeNode current, TreeNode parent, boolean right, int newValue, Function func) {
        if (current == null) {
            if (parent == null) {
                root = getNewTreeNode(newValue);
                return root;
            }
            return getNewTreeNode(newValue, parent, right);
        }
        if (newValue == current.value) { return null; }
        if (newValue < current.value) { return go(LEFT, newValue, current, func); }
        else { return go(RIGHT, newValue, current, func); }
    }

    /**
     * An overloaded version that takes only two arguments; can be called with a different Function object
     * @param newValue a value to add
     * @param func a Function object (you can write another class that extends Function, override
     *             the fix method and thus change the behaviour of this method)
     * @return
     */
    TreeNode addHelper(int newValue, Function func) { return addHelper(root, root, RIGHT, newValue, func); }

    /**
     * An overloaded version that takes only one argument from which recursion starts
     * @param newValue a value to add
     * @return a newly added TreeNode; null iff there already a node with the newValue
     */
    TreeNode addHelper(int newValue) { return addHelper(root, root, RIGHT, newValue, new Function()); }

    /**
     * Takes an appropriate child and calls addHelper on him and then updates height when needed
     * @param right a boolean value that indicates whether we go to the left or to the right child
     * @param newValue a value to add
     * @param current the node which we're looking at the current recursion level
     * @param func a Function object whose fix method is called
     * @return a newly added TreeNode; null iff there already a node with the newValue
     */
    private TreeNode go(boolean right, int newValue, TreeNode current, Function func) {
        TreeNode child = getChild(current, right);
        TreeNode added = addHelper(child, current, right, newValue, func);
        // we need to reassign here because there can be old null link (as child stored value before adding a new node)
        child = getChild(current, right);
        if (added != null) {
            func.fix(current, child);
            return added;
        }
        return null;
    }

    /**
     * Simple method to get the left/right child
     * @param node the parent node
     * @param right a boolean value that indicates which child do we need
     * @return an appropriate child
     */
    private TreeNode getChild(TreeNode node, boolean right) {
        // actually we don't need this line, but maybe we will use this method somewhere where node can be null
        if (node == null) return null;
        if (right) return node.right;
        else return node.left;
    }

    //------------------------------------------------------------------------------------------------
    //-------------------------------------------- size ----------------------------------------------
    //------------------------------------------------------------------------------------------------

    /** @return number of nodes in the tree */
    public int size() { return size; }

    //------------------------------------------------------------------------------------------------
    //------------------------------------------- iterator -------------------------------------------
    //------------------------------------------------------------------------------------------------

    /**
     * Returns an iterator for the Tree. The returned iterator iterates
     * over the tree nodes in an ascending order, and does NOT implement the remove() method.
     * @return an iterator for the Tree.
     * @throws NoSuchElementException iff next is called while there is no element left to iterate over
     */
    public java.util.Iterator<Integer> iterator() throws NoSuchElementException {
        class TreeIterator implements Iterator<Integer> {

            private TreeNode current;

            @Override
            public boolean hasNext() {
                if (current == null) { return true; }
                return successor(current) != null;
            }

            @Override
            public Integer next() {
                if (current == null) { current = getMinTreeNode(root); }
                else { current = successor(current); }
                if (current == null) { throw new NoSuchElementException(); }
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
            else return getFirstRightTurn(node);
        }
        else return getMinTreeNode(node.right);
    }

    /**
     * Goes up in the tree from the given node until it finds a node such that
     * the previous one is its left child (intuitively we go up-left in the tree until we finally go up-right)
     * @param node
     * @return
     */
    private TreeNode getFirstRightTurn(TreeNode node) {
        TreeNode parent = node.parent;
        while (parent != null && parent.value < node.value) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //------------------------------------------------------------------------------------------------
    //----------------------------------------- contains ---------------------------------------------
    //------------------------------------------------------------------------------------------------

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

    /**
     * Recursive helper for searching a value in the tree.
     * @param currentNode Current node
     * @param searchVal Searching value
     * @return The node which contains the searching value if such exists, otherwise null.
     */
    TreeNode searching (TreeNode currentNode, int searchVal){
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

    //------------------------------------------------------------------------------------------------
    //---------------------------------------- delete ------------------------------------------------
    //------------------------------------------------------------------------------------------------

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

    /**
     * Recursive helper for delete function, which finds node for deletion and removes it.
     * @param toDelete Value to delete
     * @param currentNode Current node
     * @return Current node when searching node is still not deleted, null when it deleted.
     */
    TreeNode deleteHelper(int toDelete, TreeNode currentNode) {
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
                    currentNode.value = getMinTreeNode(currentNode.right).value;
                    currentNode.right = deleteHelper(currentNode.value, currentNode.right);
                } else {
                    if (currentNode.left != null) {
                        currentNode = currentNode.left;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
        return currentNode;
    }

    /**
     * Corrects the height parameter in all nodes in current subtree.
     * @param node Current node
     * @return the height of the current node
     */
    int heightCheck(TreeNode node) {
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

    //------------------------------------------------------------------------------------------------
    //--------------------------------------- findMaxNodes -------------------------------------------
    //------------------------------------------------------------------------------------------------

    /**
     * A method that calculates the maximum number of nodes in a tree of height h
     * @param h - height of the tree (a non-negative number).
     * @return maximum number of nodes in a tree of height h
     */
    public static int findMaxNodes(int h) {
        int result = 0;
        for (int i = 0; i <= h; i++) {
            result += Math.pow(2, i);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------
    //------------------------------------ array representation --------------------------------------
    //------------------------------------------------------------------------------------------------

    /**
     * Creates an array of TreeNode that represents the given tree
     * @param tree a tree representation of which we want
     * @param copy determines whether this method is in copy mode or not; it doesn't need to be because
     *             you may want just to print all elements (therefore you don't need to copy but just to traverse)
     * @return an array of TreeNode representing the given tree
     */
    TreeNode[] toArray(Tree tree, boolean copy) {
        TreeNode[] result = new TreeNode[findMaxNodes(tree.height())];
        traverseToArray(1, result, tree.root, copy);
        return result;
    }

    /**
     * Recursively inserts nodes to the given array on the appropriate indices.
     * @param i number of the current node in the array (starting from 1)
     * @param result the array in which we insert the nodes
     * @param current the node which we're looking at the current recursion level
     * @param copy determines whether this method is in copy mode or not
     */
    void traverseToArray(int i, TreeNode[] result, TreeNode current, boolean copy) {
        if (current == null) { return; }
        if (copy) { getNewTreeNode(current.value, current.height, result, i); }
        else { result[i - 1] = current; }
        traverseToArray(i * 2, result, current.left, copy);
        traverseToArray(i * 2 + 1, result, current.right, copy);
    }

    //------------------------------------------------------------------------------------------------
    //--------------------------------------- Constructors -------------------------------------------
    //------------------------------------------------------------------------------------------------

    /** A default constructor */
    public Tree() { this.root = null; }

    /** A constructor that builds the tree by adding the elements in the input array one-by-one.
     * If the same values appears twice (or more) in the list, it is ignored.
     * @param data elements to put into the tree
     * */
    public Tree(int[] data) {
        this();
        for (int element : data) {
            this.add(element);
        }
    }

    /** A copy constructor that builds the tree from existing tree.
     * @param tree - tree to be copied
     */
    public Tree(Tree tree) {
        this();
        TreeNode[] array = toArray(tree, COPY);
        if (array.length != 0 && array[0] != null) { root = array[0]; }
    }

    //------------------------------------------------------------------------------------------------
    //-------------------------------------- Other (print)  ------------------------------------------
    //------------------------------------------------------------------------------------------------
    // TODO: THESE METHODS AREN'T REQUIRED AND NEED TO BE CUT OFF AT THE END

//    public String toString() {
//        if (root == null) return "";
//
//        TreeNode[] arrayTree = toArray(this, false);
//        String result = "";
//        int spaceNumber = height();
//
//        String currentHeightString = "";
//        String currentSpaces = "";
//        for (int i = 0; i < arrayTree.length; i++) {
//            if (isPowerOfTwo(i + 1)) {
//                if (spaceNumber != height()) {
//                    currentHeightString += currentSpaces + "\n";
//                    result += currentHeightString;
//                }
//                currentSpaces = getNumberOfSpaces(spaceNumber);
//                spaceNumber--;
//                currentHeightString = currentSpaces;
//            }
//            String element;
//            if (arrayTree[i] == null) { element = " "; }
//            else { element = Integer.toString(arrayTree[i].value); }
//            currentHeightString += element + " ";
//        }
//        result += currentHeightString.substring(0, currentHeightString.length() - 1);
//        result += "\nsize: " + size();
//
//        return result;
//    }
//
//    private String getNumberOfSpaces(int number) {
//        String result = "";
//        for (int i = 0; i < number; i++) { result += " "; }
//        return result;
//    }
//
//    private boolean isPowerOfTwo(int i) {
//        if (i == 1) return true;
//        if (i % 2 == 0) return isPowerOfTwo(i / 2);
//        return false;
//    }
}
