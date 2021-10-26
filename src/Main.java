import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Node<String> A = new Node<>("a");

        Node<String> B = A.addChild(new Node<String>("b"));
        Node<String> C = A.addChild(new Node<String>("c"));
        Node<String> D = A.addChild(new Node<String>("d"));

        Node<String> E = B.addChild(new Node<String>("e"));
        Node<String> F = B.addChild(new Node<String>("f"));

        Node<String> G = D.addChild(new Node<String>("g"));
        Node<String> H = D.addChild(new Node<String>("h"));
        Node<String> I = D.addChild(new Node<String>("i"));

        Node<String> J = E.addChild(new Node<String>("j"));
        Node<String> K = E.addChild(new Node<String>("k"));
        Node<String> L = E.addChild(new Node<String>("l"));

        Node<String> M = G.addChild(new Node<String>("m"));
        Node<String> N = I.addChild(new Node<String>("n"));
        Node<String> O = I.addChild(new Node<String>("o"));

        Node<String> P = M.addChild(new Node<String>("p"));

        //printTree(A, "--");
        //A.breadthFirstSearch(A);
        A.suffixSearch(A, new ArrayList<Node>());
    }
    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }
}
