package oop.ex4.data_structures;

/**
 * This class (specifically, its method fix) is used in the go method of the Tree class
 * when going back recursively after adding a new node in the addHelper method.
 * It's good to have it since we can inherit from it another class (with its own implementation
 * of the fix method) and do stuff while we are still deep in the tree instead of
 * going up with go/addHelper recursion, then going down with some other fix method, and after that
 * going up again.
 */
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
