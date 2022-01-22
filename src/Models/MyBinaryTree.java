package Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyBinaryTree {
    private MyBinaryNode root;
    private List<Integer> infixDepthCourseList;
    private List<Integer> postfixDepthCourseList;
    private List<Integer> prefixDepthCourseList;

    public MyBinaryTree(MyBinaryNode root) {
        this.root = root;
    }


    //region Depth Courses
    //Parcours en largeur
    public static List<MyBinaryNode> widthCourse(MyBinaryNode node) {
        Queue<MyBinaryNode> currentNodes  = new LinkedList<>();
        currentNodes.add(node);

        List<MyBinaryNode> result = new ArrayList<MyBinaryNode>();
        while (!currentNodes.isEmpty()) {
            MyBinaryNode currentNode = currentNodes.remove();
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
    public List<Integer> infixDepthCourse(MyBinaryNode<Integer> node) {
        infixDepthCourseList = new ArrayList<Integer>();
        infixDepthCourseRecursive(node);
        return infixDepthCourseList;
    }

    private void infixDepthCourseRecursive(MyBinaryNode<Integer> node) {
        if(node.getLeftChild() != null) {
            infixDepthCourseRecursive(node.getLeftChild());
        }
        infixDepthCourseList.add(node.getData());

        if(node.getRightChild() != null) {
            infixDepthCourseRecursive(node.getRightChild());
        }
    }

    //Prefix depth course for a Binary Models.Tree
    public List<Integer> prefixDepthCourse(MyBinaryNode<Integer> node) {
        prefixDepthCourseList = new ArrayList<Integer>();
        prefixDepthCourseListRecursive(node);
        return prefixDepthCourseList;
    }

    private void prefixDepthCourseListRecursive(MyBinaryNode<Integer> node) {
        prefixDepthCourseList.add(node.getData());
        if(node.getLeftChild() != null) {
            prefixDepthCourseListRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            prefixDepthCourseListRecursive(node.getRightChild());
        }
    }

    //Postfix depth course for a Binary Models.Tree
    public List<Integer> postfixDepthCourse(MyBinaryNode<Integer> node) {
        postfixDepthCourseList = new ArrayList<Integer>();
        postfixDepthCourseRecursive(node);
        return postfixDepthCourseList;
    }

    private void postfixDepthCourseRecursive(MyBinaryNode<Integer> node) {
        if(node.getLeftChild() != null) {
            postfixDepthCourseRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            postfixDepthCourseRecursive(node.getRightChild());
        }
        postfixDepthCourseList.add(node.getData());
    }
    //endregion Depth Courses


    public MyBinaryNode getRoot() {
        return root;
    }

    public void setRoot(MyBinaryNode root) {
        this.root = root;
    }

    public static <T> void print(MyBinaryNode<T> myBinaryNode, String appender) {
        System.out.println(appender + myBinaryNode.getData());
        if(myBinaryNode.getLeftChild() != null) {
            print(myBinaryNode.getLeftChild(), appender + appender);
        }

        if(myBinaryNode.getRightChild() != null) {
            print(myBinaryNode.getRightChild(), appender + appender);
        }
    }
}
