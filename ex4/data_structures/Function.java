package oop.ex4.data_structures;

public class Function {

    /**
     * Fixes the given node and its child. Here fixes only height; extend and override this to add functionality
     * @param current a node to fix
     * @param child its child
     */
    public void fix(Tree.TreeNode current, Tree.TreeNode child) {
        adjustHeight(current, child);
    }


    /**
     * Increments height while needed (it is needed in go method in Tree when a new node is added
     * and it goes back to the root recursively)
     * @param current a node to adjust its height
     * @param child a node's child
     */
    private void adjustHeight(Tree.TreeNode current, Tree.TreeNode child) {
        if (current.height <= child.height) { current.height++; }
    }
}
