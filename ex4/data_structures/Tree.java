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

    /** @return the height of the tree */
    private int height() { return root.height; }
    
    /** A default constructor */
    public Tree() { this.root = null; }

    /** A constructor that builds the tree by adding the elements in the input array one-by-one.
     * If the same values appears twice (or more) in the list, it is ignored. */
    public Tree(int[] data) {
        this.root = new TreeNode(data[0]);
    }

    // TODO: THESE METHODS AREN'T REQUIRED AND NEED TO BE CUT OFF AT THE END
    private int[] toArray() {
        int[] result = new int[(int)Math.pow(2, height())];

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
            currentHeightString += arrayTree[i] + " ";
        }
        result += currentHeightString.substring(0, currentHeightString.length() - 1);

        return result;
    }

    private String getNumberOfSpaces(int number) {
        String result = "";
        for (int i = 0; i < number; i++) { result += " "; }
        return result;
    }

    private boolean isPowerOfTwo(int i) { return i == 1 || i % 2 == 0; }
}
