import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> listOfKeys = new ArrayList<Integer>();
        listOfKeys = Arrays.asList(10, 5, 12, 2, 7, 15, 4, 17);

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.listToTree(listOfKeys);

        binarySearchTree.print(binarySearchTree.getRoot(), "-");

        System.out.println("*******************************");

        binarySearchTree.getRoot().simpleLeftRotate(binarySearchTree.getRoot().getRightChild());
        binarySearchTree.print(binarySearchTree.getRoot(), "-");


    }

}
