
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Node<T> {

    private T data = null;

    private List<Node<T>> children = new ArrayList<>();

    private Node<T> parent = null;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    //Parcours en largeur
    public List<Node> breadthFirstSearch(Node node) {
        Queue<Node> currentNodes  = new LinkedList<>();
        currentNodes.add(node);

        List<Node> result = new ArrayList<Node>();
        while (!currentNodes.isEmpty()) {
            Node currentNode = currentNodes.remove(); //We make one item go out
            result.add(currentNode);
            System.out.println("[breadthFirstSearch] Adding node " + currentNode.data + " to result");
            currentNode.children.forEach(child -> currentNodes.add((Node) child));
        }
        return result;
    }

    //Parcours en profondeur préfixe
    public List<Node> depthFirstSearch(Node node, List<Node> result) {
        result.add(node);
        System.out.println("[depthFirstSearch] Adding node " + node.data + " to result");
        node.children.forEach(
                child -> depthFirstSearch((Node) child, result)
        );
        return result;
    }

    //Parcours en profondeur suffixe
    public List<Node> suffixSearch(Node node, List<Node> result) {
        node.children.forEach(
                child -> suffixSearch((Node) child, result)
        );
        result.add(node);
        System.out.println("[suffixSearch] Adding node " + node.data + " to result");
        return result;
    }

}