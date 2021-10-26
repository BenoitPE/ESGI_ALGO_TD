# ALGO TD1

###### tags: `ESGI`

## Exercice 1:
1. Racine : a
2. Nb branche : 15
3. Hauteur : 5

```java=
import java.util.ArrayList;
import java.util.List;

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

}
```

```java=
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

        printTree(A, "--");

    }
    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each ->  printTree(each, appender + appender));
    }
}
```

## Exercice 2:

1.
Parcours en largeur: `A B C D E F G H I J K L M N O P`
Parcours en profondeur préfixe: `A B E J K L F C D G M P H I N O`
Parcours en profondeur suffixe: `J K L E F B C P M G H N O I D A`
Parcours en profondeur infixe: Impossible(pas un arbre binaire)
2.
Parcours en largeur: `20 5 25 3 12 21 28 8 13 6`
Parcours en profondeur préfixe: `20 5 3 12 8 6 13 25 21 28`
Parcours en profondeur suffixe: `3 6 8 12 13 5 21 28 25 20`
Parcours en profondeur infixe: `3 5 6 8 12 13 20 21 25 28`
