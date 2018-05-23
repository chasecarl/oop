package oop.ex4.data_structures;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {

        Tree tree = new Tree();
        System.out.println(tree.size());
        int num = 5;
        //for (int i = 0; i < 5; i++) {
          //  System.out.println(addandprint(tree, num - i));
            //System.out.println(addandprint(tree, num + i));
//  //          System.out.println(i + Boolean.toString(tree.isPowerOfTwoWrapper(i)));
        //}
        System.out.println(addandprint(tree, 11));
        System.out.println(addandprint(tree, 5));
        System.out.println(addandprint(tree, 17));
        System.out.println(addandprint(tree, 3));
        System.out.println(addandprint(tree, 9));
        System.out.println(addandprint(tree, 15));
        System.out.println(addandprint(tree, 21));
        System.out.println(addandprint(tree, 1));
        System.out.println(addandprint(tree, 7));
        System.out.println(addandprint(tree, 13));
        System.out.println(addandprint(tree, 19));
        System.out.println(deleteAndPrint(tree, 5));
        //System.out.println(deleteAndPrint(tree, 666));
        //System.out.println(deleteAndPrint(tree, 5));
        //System.out.println(deleteAndPrint(tree, 9));
        //System.out.println(deleteAndPrint(tree, 1));
        //System.out.println(deleteAndPrint(tree, 6));
        //for (int i = 1; i < 10; i++){
        //    conainsAndPrint(tree, i);
        //}
        //TODO After deletion of the element the height of the node doesn't changes, HOW TO SOLVE IT???
        //1 3 /5/ 7 9 /11/ 13 15 /17/ 19 21

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
