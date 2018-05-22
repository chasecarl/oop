package oop.ex4.data_structures;

public class AvlTree extends Tree {

    private void heightCorrection(TreeNode node){
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight > rightHeight){
            node.height = leftHeight + 1;
        } else {
            node.height = rightHeight + 1;
        }
    }
    private TreeNode rightRotation(TreeNode node){
        TreeNode X = node;
        TreeNode Y = node.left;
        X.left = Y.right;
        Y.right = X;
        heightCorrection(X);
        heightCorrection(Y);
        return Y;
    }

    private TreeNode leftRotation(TreeNode node){
        TreeNode X = node.right;
        TreeNode Y = node;
        Y.right = X.left;
        X.left = Y;
        heightCorrection(Y);
        heightCorrection(X);
        return X;
    }


    private int balanceFactor(TreeNode node){
        return getHeight(node.right) - getHeight(node.left);
    }

    private TreeNode correction(TreeNode node){
        heightCorrection(node);
        if (balanceFactor(node) == 2){
            if (balanceFactor(node.right) < 0){
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        } else {
            if (balanceFactor(node) == -2){
                if (balanceFactor(node.left) > 0){
                    node.left = leftRotation(node.left);
                    return rightRotation(node);
                }
            }
        }
        return node;
    }


    public boolean add(int newValue){
        super.add(newValue);
        //TODO To add correction of node's height
        //return correction(//TODO To add the node, which was changed, here)
         return true; //TODO To delete this line later
    }

    public boolean delete(int toDelete){
        TreeNode node = searching(this.root, toDelete);
        if (node != null){
            correction(deleteHelper(toDelete, root));
            size--;
            return true;
        } else {
            return false;
        }
    }

}
