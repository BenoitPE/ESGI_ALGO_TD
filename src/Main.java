import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree(new BinarySearchNode(20));

        binarySearchTree.insert(new BinarySearchNode(5));
        binarySearchTree.insert(new BinarySearchNode(25));
        binarySearchTree.insert(new BinarySearchNode(3));
        binarySearchTree.insert(new BinarySearchNode(12));
        binarySearchTree.insert(new BinarySearchNode(21));
        binarySearchTree.insert(new BinarySearchNode(28));
        binarySearchTree.insert(new BinarySearchNode(8));
        binarySearchTree.insert(new BinarySearchNode(13));
        binarySearchTree.insert(new BinarySearchNode(6));

        binarySearchTree.infixSearch(binarySearchTree.getRoot());

    }

}
