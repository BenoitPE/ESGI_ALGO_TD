import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree(new BinarySearchNode(20));

        binarySearchTree.insert(new BinarySearchNode(25));
        binarySearchTree.insert(new BinarySearchNode(60));
        binarySearchTree.insert(new BinarySearchNode(35));
        binarySearchTree.insert(new BinarySearchNode(10));
        binarySearchTree.insert(new BinarySearchNode(5));
        binarySearchTree.insert(new BinarySearchNode(20));
        binarySearchTree.insert(new BinarySearchNode(65));
        binarySearchTree.insert(new BinarySearchNode(45));
        binarySearchTree.insert(new BinarySearchNode(70));
        binarySearchTree.insert(new BinarySearchNode(40));
        binarySearchTree.insert(new BinarySearchNode(50));
        binarySearchTree.insert(new BinarySearchNode(55));
        binarySearchTree.insert(new BinarySearchNode(30));
        binarySearchTree.insert(new BinarySearchNode(15));


        binarySearchTree.infixSearch(binarySearchTree.getRoot());

    }

}
