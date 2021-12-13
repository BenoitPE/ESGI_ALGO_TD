import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> listOfKeys = new ArrayList<Integer>();
        listOfKeys = Arrays.asList(11, 8, 14,5, 10, 13, 15,17);

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.listToTree(listOfKeys);

        binarySearchTree.print(binarySearchTree.getRoot(), "-");
        System.out.println(binarySearchTree.getRoot().balanceFactor(binarySearchTree.getRoot()));
        System.out.println(binarySearchTree.isBalanceTree());
    }

}
