package Models.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    private BinaryNode root;
    private List<Integer> infixDepthCourseList;
    private List<Integer> postfixDepthCourseList;
    private List<Integer> prefixDepthCourseList;

    public BinaryTree(BinaryNode root) {
        this.root = root;
    }


    //region Depth Courses
    //Parcours en largeur
    public static List<BinaryNode> widthCourse(BinaryNode node) {
        Queue<BinaryNode> currentNodes  = new LinkedList<>();
        currentNodes.add(node);

        List<BinaryNode> result = new ArrayList<BinaryNode>();
        while (!currentNodes.isEmpty()) {
            BinaryNode currentNode = currentNodes.remove();
            result.add(currentNode);
            System.out.print(currentNode.getData() + " ");
            if(currentNode.getLeftChild() != null)
                currentNodes.add(currentNode.getLeftChild());
            if(currentNode.getRightChild() != null)
                currentNodes.add(currentNode.getRightChild());
            //currentNode.getChildren().forEach(child -> currentNodes.add((Models.Node) child));
        }
        return result;
    }

    //Infix depth course for a Binary Models.Tree
    public List<Integer> infixDepthCourse(BinaryNode<Integer> node) {
        infixDepthCourseList = new ArrayList<Integer>();
        infixDepthCourseRecursive(node);
        return infixDepthCourseList;
    }

    private void infixDepthCourseRecursive(BinaryNode<Integer> node) {
        if(node.getLeftChild() != null) {
            infixDepthCourseRecursive(node.getLeftChild());
        }
        infixDepthCourseList.add(node.getData());

        if(node.getRightChild() != null) {
            infixDepthCourseRecursive(node.getRightChild());
        }
    }

    //Prefix depth course for a Binary Models.Tree
    public List<Integer> prefixDepthCourse(BinaryNode<Integer> node) {
        prefixDepthCourseList = new ArrayList<Integer>();
        prefixDepthCourseListRecursive(node);
        return prefixDepthCourseList;
    }

    private void prefixDepthCourseListRecursive(BinaryNode<Integer> node) {
        prefixDepthCourseList.add(node.getData());
        if(node.getLeftChild() != null) {
            prefixDepthCourseListRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            prefixDepthCourseListRecursive(node.getRightChild());
        }
    }

    //Postfix depth course for a Binary Models.Tree
    public List<Integer> postfixDepthCourse(BinaryNode<Integer> node) {
        postfixDepthCourseList = new ArrayList<Integer>();
        postfixDepthCourseRecursive(node);
        return postfixDepthCourseList;
    }

    private void postfixDepthCourseRecursive(BinaryNode<Integer> node) {
        if(node.getLeftChild() != null) {
            postfixDepthCourseRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            postfixDepthCourseRecursive(node.getRightChild());
        }
        postfixDepthCourseList.add(node.getData());
    }
    //endregion Depth Courses


    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    public static <T> void print(BinaryNode<T> binaryNode, String appender) {
        System.out.println(appender + binaryNode.getData());
        if(binaryNode.getLeftChild() != null) {
            print(binaryNode.getLeftChild(), appender + appender);
        }

        if(binaryNode.getRightChild() != null) {
            print(binaryNode.getRightChild(), appender + appender);
        }
    }
}
