package oop.ex4.data_structures;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
//        int[] data = { 0 };
        Tree tree = new Tree();
        int num = 5;
        for (int i = 0; i < 5; i++) {
            System.out.println(addandprint(tree, num - i));
            System.out.println(addandprint(tree, num + i));
//            System.out.println(i + Boolean.toString(tree.isPowerOfTwoWrapper(i)));
        }

        Iterator<Integer> iterator = tree.iterator();
        System.out.println(iterator.hasNext());
        while(iterator.hasNext()) {
            System.out.println(iterator.hasNext());
            System.out.println(iterator.next());
        }
        System.out.println(iterator.hasNext());
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
