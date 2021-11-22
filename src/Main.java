import java.util.*;

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
        binarySearchTree.infixDepthCourse(binarySearchTree.getRoot());
        long timeElapsedBinarySearchTree = System.nanoTime() - startBinarySearchTree;

        long start = System.nanoTime();
        Collections.sort(listOfKeys);
        long timeElapsed = System.nanoTime() - start;

        System.out.println("timeElapsed to sort with a binary tree: \t \t" + timeElapsedBinarySearchTree);
        System.out.println("timeElapsed to sort with Collections.sort(): \t" + timeElapsed);
    }

}
