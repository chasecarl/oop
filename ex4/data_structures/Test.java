package oop.ex4.data_structures;

public class Test {
    public static void main(String[] args) {
//        int[] data = { 0 };
        Tree tree = new Tree();
        for (int i = 1; i < 10; i++) {
            tree.add(i);
            System.out.println("\nAdded " + i + ":\n");
            System.out.println(tree);
//            System.out.println(i + Boolean.toString(tree.isPowerOfTwoWrapper(i)));
        }

    }
}
