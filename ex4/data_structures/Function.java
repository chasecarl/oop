package oop.ex4.data_structures;

public class Function {
    public void fix(Tree.TreeNode current, Tree.TreeNode child) {
        adjustHeight(current, child);
    }

    private void adjustHeight(Tree.TreeNode current, Tree.TreeNode child) {
        if (current.height <= child.height) { current.height++; }
    }
}
