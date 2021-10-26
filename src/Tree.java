import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    //Parcours en largeur
    public List<Node> breadthFirstSearch(Node node) {
        Queue<Node> currentNodes  = new LinkedList<>();
        currentNodes.add(node);

        List<Node> result = new ArrayList<Node>();
        while (!currentNodes.isEmpty()) {
            Node currentNode = currentNodes.remove(); //We make one item go out
            result.add(currentNode);
            System.out.println("[breadthFirstSearch] Adding node " + currentNode.getData() + " to result");
            currentNode.getChildren().forEach(child -> currentNodes.add((Node) child));
        }
        return result;
    }

    //Parcours en profondeur préfixe
    public List<Node> depthFirstSearch(Node node, List<Node> result) {
        result.add(node);
        System.out.println("[depthFirstSearch] Adding node " + node.getData() + " to result");
        node.getChildren().forEach(
                child -> depthFirstSearch((Node) child, result)
        );
        return result;
    }

    //Parcours en profondeur suffixe
    public List<Node> suffixSearch(Node node, List<Node> result) {
        node.getChildren().forEach(
                child -> suffixSearch((Node) child, result)
        );
        result.add(node);
        System.out.println("[suffixSearch] Adding node " + node.getData() + " to result");
        return result;
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
