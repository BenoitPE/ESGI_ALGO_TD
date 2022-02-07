package Models.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {
        this.root = null;
    }

    //Parcours en largeur
    public static List<Node> widthCourse(Node node) {
        Queue<Node> currentNodes  = new LinkedList<>();
        currentNodes.add(node);

        List<Node> result = new ArrayList<Node>();
        while (!currentNodes.isEmpty()) {
            Node currentNode = currentNodes.remove();
            result.add(currentNode);
            System.out.print(currentNode.getData() + " ");
            currentNode.getChildren().forEach(child -> currentNodes.add((Node) child));
        }
        return result;
    }

    //Parcours en profondeur préfixe
    public static List<Node> prefixDepthCourse(Node node, List<Node> result) {
        result.add(node);
        System.out.print(node.getData() + " ");

        node.getChildren().forEach(
                child -> prefixDepthCourse((Node) child, result)
        );
        return result;
    }

    //Parcours en profondeur suffixe
    public static List<Node> postfixDepthCourse(Node node, List<Node> result) {
        node.getChildren().forEach(
                child -> postfixDepthCourse((Node) child, result)
        );
        result.add(node);
        System.out.print(node.getData() + " ");

        return result;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static <T> void print(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  print(each, appender + appender));
    }
}
