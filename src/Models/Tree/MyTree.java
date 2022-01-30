package Models.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyTree {
    private MyNode root;

    public MyTree(MyNode root) {
        this.root = root;
    }

    public MyTree() {
        this.root = null;
    }

    //Parcours en largeur
    public static List<MyNode> widthCourse(MyNode myNode) {
        Queue<MyNode> currentNodes  = new LinkedList<>();
        currentNodes.add(myNode);

        List<MyNode> result = new ArrayList<MyNode>();
        while (!currentNodes.isEmpty()) {
            MyNode currentNode = currentNodes.remove();
            result.add(currentNode);
            System.out.print(currentNode.getData() + " ");
            currentNode.getChildren().forEach(child -> currentNodes.add((MyNode) child));
        }
        return result;
    }

    //Parcours en profondeur préfixe
    public static List<MyNode> prefixDepthCourse(MyNode myNode, List<MyNode> result) {
        result.add(myNode);
        System.out.print(myNode.getData() + " ");

        myNode.getChildren().forEach(
                child -> prefixDepthCourse((MyNode) child, result)
        );
        return result;
    }

    //Parcours en profondeur suffixe
    public static List<MyNode> postfixDepthCourse(MyNode myNode, List<MyNode> result) {
        myNode.getChildren().forEach(
                child -> postfixDepthCourse((MyNode) child, result)
        );
        result.add(myNode);
        System.out.print(myNode.getData() + " ");

        return result;
    }

    public MyNode getRoot() {
        return root;
    }

    public void setRoot(MyNode root) {
        this.root = root;
    }

    public static <T> void print(MyNode<T> myNode, String appender) {
        System.out.println(appender + myNode.getData());
        myNode.getChildren().forEach(each ->  print(each, appender + appender));
    }
}
