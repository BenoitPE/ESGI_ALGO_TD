import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int MAXBOUND = 10000;

    public static void main(String[] args) {
        List<Integer> listOfKeys = new ArrayList<Integer>();
        Random rand = new Random();
        for(int i=0; i< MAXBOUND; i++) {
            listOfKeys.add(rand.nextInt(MAXBOUND));
        }
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.listToTree(listOfKeys);

        long startBinarySearchTree = System.nanoTime();
        for (int i=0; i< 100; i++) {
            binarySearchTree.search(binarySearchTree.getRoot(), new BinarySearchNode(rand.nextInt(MAXBOUND)));
        }
        long timeElapsedBinarySearchTree = System.nanoTime() - startBinarySearchTree;


        long start = System.nanoTime();
        for (int i=0; i< 100; i++) {
            listOfKeys.contains(rand.nextInt(MAXBOUND));
        }
        long timeElapsed = System.nanoTime() - start;

        System.out.println("timeElapsed to search in a binary tree: " + timeElapsedBinarySearchTree);
        System.out.println("timeElapsed to search using contains(): " + timeElapsed);
    }

}
