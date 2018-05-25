package oop.ex4.data_structures;

public class Function {
    public void doAll(Tree.TreeNode current, Tree.TreeNode next) {
        adjustHeight(current, next);
    }

    protected void adjustHeight(Tree.TreeNode current, Tree.TreeNode next) {
        if (current.height <= next.height) { current.height++; }
    }
}
