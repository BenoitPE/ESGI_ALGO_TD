import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> listOfKeys = new ArrayList<Integer>();
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        //listOfKeys = Arrays.asList(3, 1, 5, 4, 7, 6, 9);
        listOfKeys = Arrays.asList(10, 5, 12, 2, 7, 15, 4, 17);
        binarySearchTree.listToTree(listOfKeys);

        binarySearchTree.print(binarySearchTree.getRoot().getRightChild(), "-");
        System.out.println("Facteur d'équilibrage: " + BinarySearchNode.balanceFactor(binarySearchTree.getRoot().getRightChild()));

        System.out.println("*******************************");

        BinarySearchTree.print(BinarySearchNode.simpleLeftRotate(binarySearchTree.getRoot().getRightChild()), "-");
        System.out.println("Facteur d'équilibrage: " + BinarySearchNode.balanceFactor(BinarySearchNode.simpleLeftRotate(binarySearchTree.getRoot().getRightChild())));

    }

}
