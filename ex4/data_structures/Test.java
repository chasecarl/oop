package oop.ex4.data_structures;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {

        Tree tree = new Tree();
        System.out.println(tree.size());
        int num = 5;
        for (int i = 0; i < 5; i++) {
            System.out.println(addandprint(tree, num - i));
            System.out.println(addandprint(tree, num + i));
//            System.out.println(i + Boolean.toString(tree.isPowerOfTwoWrapper(i)));
        }

//        Iterator<Integer> iterator = tree.iterator();
//        System.out.println(iterator.hasNext());
//        while(iterator.hasNext()) {
//            System.out.println(iterator.hasNext());
//            System.out.println(iterator.next());
//        }
//        System.out.println(iterator.hasNext());

        int[] array = { 10, 5, 15, 2, 7, 1, 3, 6, 8, 12, 20, 11, 13, 18, 25 };
        Tree arrayTree = new Tree(array);
        System.out.println(arrayTree);

        Iterator<Integer> iterator2 = arrayTree.iterator();
        System.out.println(iterator2.hasNext());
        while(iterator2.hasNext()) {
            System.out.println(iterator2.next());
            System.out.println(iterator2.hasNext());
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
