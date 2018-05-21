package oop.ex4.data_structures;

public class Test {
    public static void main(String[] args) {
//        int[] data = { 0 };
        Tree tree = new Tree();
        System.out.println(tree.size());
        int num = 5;
        for (int i = 0; i < 5; i++) {
            System.out.println(addandprint(tree, num - i));
            System.out.println(addandprint(tree, num + i));
//            System.out.println(i + Boolean.toString(tree.isPowerOfTwoWrapper(i)));
        }
        System.out.println(deleteAndPrint(tree, 666));
        System.out.println(deleteAndPrint(tree, 5));
        //System.out.println(deleteAndPrint(tree, 9));
        System.out.println(deleteAndPrint(tree, 1));
        System.out.println(deleteAndPrint(tree, 6));
        for (int i = 1; i < 10; i++){
            conainsAndPrint(tree, i);
        }
        //TODO After deletion of the element the height of the node doesn't changes, HOW TO SOLVE IT???



    }

    public static boolean addandprint(Tree tree, int newValue) {
        boolean result = tree.add(newValue);
        if (result) {
            System.out.println("\nAdded " + newValue + ":\n");
        }
        System.out.println(tree);
        return result;
    }

    public static boolean deleteAndPrint(Tree tree, int deleteValue){
        boolean result = tree.delete(deleteValue);
        if (result){
            System.out.println("\nDeleted " + deleteValue + ":\n");
        }
        System.out.println(tree);
        return result;
    }

    public static void conainsAndPrint(Tree tree, int searchVal){
        int result = tree.contains(searchVal);
        System.out.println("\nValue " + searchVal + " is contained:\n");
        System.out.println(tree);
        System.out.println("Height:" + result);
    }

}
