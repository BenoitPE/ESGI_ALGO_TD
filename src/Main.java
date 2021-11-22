import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Integer> listOfKeys = new ArrayList<Integer>();
        Random rand = new Random();
        for(int i=0; i< 1000; i++) {
            listOfKeys.add(rand.nextInt(2000));
        }

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.listToTree(listOfKeys);
        binarySearchTree.infixSearch(binarySearchTree.getRoot());
    }

}
