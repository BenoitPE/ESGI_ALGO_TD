import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BinaryNode<Integer> i20 = new BinaryNode<>(20);
        BinaryNode<Integer> i5 = i20.setLeftChild(new BinaryNode<Integer>(5));
        BinaryNode<Integer> i25 = i20.setRightChild(new BinaryNode<Integer>(25));

        BinaryNode<Integer> i3 = i5.setLeftChild(new BinaryNode<Integer>(3));
        BinaryNode<Integer> i12 = i5.setRightChild(new BinaryNode<Integer>(12));

        BinaryNode<Integer> i8 = i12.setLeftChild(new BinaryNode<Integer>(8));
        BinaryNode<Integer> i13 = i12.setRightChild(new BinaryNode<Integer>(13));

        BinaryNode<Integer> i6 = i8.setLeftChild(new BinaryNode<Integer>(6));

        BinaryNode<Integer> i21 = i25.setLeftChild(new BinaryNode<Integer>(21));
        BinaryNode<Integer> i28 = i25.setRightChild(new BinaryNode<Integer>(28));

        BinaryTree binaryTree = new BinaryTree(i20);
        printBinaryTree(i20, "-");

    }
    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }

    private static <T> void printBinaryTree(BinaryNode<T> binaryNode, String appender) {
        System.out.println(appender + binaryNode.getData());
        if(binaryNode.getLeftChild() != null) {
            printBinaryTree(binaryNode.getLeftChild(), appender + appender);
        }

        if(binaryNode.getRightChild() != null) {
            printBinaryTree(binaryNode.getRightChild(), appender + appender);
        }
    }
}
