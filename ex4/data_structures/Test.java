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
        System.out.println(deleteAndPrint(tree, 9));
        System.out.println(deleteAndPrint(tree, 1));
        System.out.println(deleteAndPrint(tree, 6));
        for (int i = 1; i < 10; i++){
            containsAndPrint(tree, i);
        }
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

        int[] someArray = { 1, 3, 0, -1, 2 };
        Tree treeOnData = new Tree(someArray);
        System.out.println(treeOnData);
        Tree copyTree = new Tree(treeOnData);
        System.out.println(copyTree);
        System.out.println(copyTree.equals(treeOnData));

        //------------------------------ AVL ----------------------------------------

        String aLotOfBars = "--------------------------------------";
        System.out.println("\n" + aLotOfBars+ "AVL" + aLotOfBars + "\n");

        int[] someArray1 = { 7, 3, 5, 6, 4 };
//        int[] someArray1 = { 1, 2, 3 };
        AvlTree avltree1 = new AvlTree(someArray1);

        System.out.println(avltree1);

//        Iterator<Integer> avlIterator = avltree1.iterator();
//
//        System.out.println(AvlTree.findMaxNodes(0));
//        System.out.println(AvlTree.findMaxNodes(5));
//        System.out.println(AvlTree.findMinNodes(0));
//        System.out.println(AvlTree.findMinNodes(1));
//        System.out.println(AvlTree.findMinNodes(3));
//        System.out.println(AvlTree.findMinNodes(4));
//        System.out.println(AvlTree.findMinNodes(5));
//        System.out.println(AvlTree.findMinNodes(10));
//
//        AvlTree avlTree1copy = new AvlTree(avltree1);
//        System.out.println(avlTree1copy);
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

    public static void containsAndPrint(Tree tree, int searchVal){
        int result = tree.contains(searchVal);
        System.out.println("\nValue " + searchVal + " is contained:\n");
        System.out.println(tree);
        System.out.println("Depth:" + result);
    }

}
