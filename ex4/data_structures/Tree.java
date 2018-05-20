package oop.ex4.data_structures;

public class Tree {

    /** The root node of a tree */
    TreeNode root;
    /** Used in addHelper method to change its behavior to the left part of the tree */
    private static final boolean LEFT = false;
    /** Used in addHelper method to change its behavior to the right part of the tree */
    private static final boolean RIGHT = true;

    /** Represents a single node of a tree */
    private class TreeNode {

        /** The left child of a node */
        private TreeNode left;
        /** The right child of a node */
        private TreeNode right;
        /** The parent of a node */
        private TreeNode parent;
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

    /** @return the height of the tree */
    private int height() { return root.height; }

    /**
     * Add a new node with key newValue into the tree
     * @param newValue new value to add to the tree
     * @return false iff newValue already exists in the tree
     */
    public boolean add(int newValue) {
        return addHelper(root, root, RIGHT, newValue);
    }

    private boolean addHelper(TreeNode current, TreeNode parent, boolean right, int newValue) {
        if (current == null) {
            if (parent == null) {
                this.root = new TreeNode(newValue);
                return true;
            }
            if (right) { parent.right = new TreeNode(newValue); }
            else { parent.left = new TreeNode(newValue); }
            return true;
        }
        if (newValue == current.value) { return false; }
        else if (newValue < current.value) {
            if (addHelper(current.left, current, LEFT, newValue)) {
                // TODO: CAN IT BE LESS??
                if (current.height <= current.left.height) { current.height++; }
                return true;
            }
            return false;
        }
        else {
            if (addHelper(current.right, current, RIGHT, newValue)) {
                // TODO: CAN IT BE LESS??
                if (current.height <= current.right.height) { current.height++; }
                return true;
            }
            return false;
        }
    }

    /** A default constructor */
    public Tree() { this.root = null; }

    /** A constructor that builds the tree by adding the elements in the input array one-by-one.
     * If the same values appears twice (or more) in the list, it is ignored. */
    public Tree(int[] data) {
        this.root = new TreeNode(data[0]);
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
