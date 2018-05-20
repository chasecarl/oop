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

    }

    public static boolean addandprint(Tree tree, int newValue) {
        boolean result = tree.add(newValue);
        if (result) {
            System.out.println("\nAdded " + newValue + ":\n");
        }
        System.out.println(tree);
        return result;
    }
}
