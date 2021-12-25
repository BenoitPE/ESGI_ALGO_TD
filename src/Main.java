import java.util.*;

public class Main {
    public static void main(String[] args) {
        Implementation_TD1_Exo1();
        Implementation_TD1_Exo2();
        Implementation_TD2();
        Implementation_TD3_Exo1();
        Implementation_TD3_Exo2();
        Implementation_TD4_Exo1();
        Implementation_TD4_Exo2();
    }

    private static void Implementation_TD1_Exo1() {
        System.out.println("\n[TD1] Implementation de l'Exercice 1");

        Node<String> A = new Node<>("a");

        Node<String> B = A.addChild(new Node<>("b"));
        Node<String> C = A.addChild(new Node<>("c"));
        Node<String> D = A.addChild(new Node<>("d"));

        Node<String> E = B.addChild(new Node<>("e"));
        Node<String> F = B.addChild(new Node<>("f"));

        Node<String> G = D.addChild(new Node<>("g"));
        Node<String> H = D.addChild(new Node<>("h"));
        Node<String> I = D.addChild(new Node<>("i"));

        Node<String> J = E.addChild(new Node<>("j"));
        Node<String> K = E.addChild(new Node<>("k"));
        Node<String> L = E.addChild(new Node<>("l"));

        Node<String> M = G.addChild(new Node<>("m"));
        Node<String> N = I.addChild(new Node<>("n"));
        Node<String> O = I.addChild(new Node<>("o"));

        Node<String> P = M.addChild(new Node<>("p"));
        Tree tree = new Tree(A);

        Tree.print(tree.getRoot(), "-");
    }

    private static void Implementation_TD1_Exo2() {
        System.out.println("\n[TD1] Implementation de l'Exercice 2");
        Node<String> A = new Node<>("a");

        Node<String> B = A.addChild(new Node<>("b"));
        Node<String> C = A.addChild(new Node<>("c"));
        Node<String> D = A.addChild(new Node<>("d"));

        Node<String> E = B.addChild(new Node<>("e"));
        Node<String> F = B.addChild(new Node<>("f"));

        Node<String> G = D.addChild(new Node<>("g"));
        Node<String> H = D.addChild(new Node<>("h"));
        Node<String> I = D.addChild(new Node<>("i"));

        Node<String> J = E.addChild(new Node<>("j"));
        Node<String> K = E.addChild(new Node<>("k"));
        Node<String> L = E.addChild(new Node<>("l"));

        Node<String> M = G.addChild(new Node<>("m"));
        Node<String> N = I.addChild(new Node<>("n"));
        Node<String> O = I.addChild(new Node<>("o"));

        Node<String> P = M.addChild(new Node<>("p"));
        Tree tree = new Tree(A);

        System.out.println("Parcours pour l'arbre de l'exercice 1");
        System.out.print("Parcours en largeur: \t\t\t\t");
        Tree.widthCourse(tree.getRoot());
        System.out.print("\nParcours en profondeur prefixe: \t");
        Tree.prefixDepthCourse(tree.getRoot(), new ArrayList<>());
        System.out.print("\nParcours en profondeur suffixe: \t");
        Tree.postfixDepthCourse(tree.getRoot(), new ArrayList<>());
        System.out.println("\nParcours en profondeur infixe: \t\tImpossible (arbre de degre 3)");

        BinaryNode<Integer> i20 = new BinaryNode<>(20);
        BinaryNode<Integer> i5 = i20.setLeftChild(new BinaryNode<>(5));
        BinaryNode<Integer> i25 = i20.setRightChild(new BinaryNode<>(25));
        BinaryNode<Integer> i3 = i5.setLeftChild(new BinaryNode<>(3));
        BinaryNode<Integer> i12 = i5.setRightChild(new BinaryNode<>(12));
        BinaryNode<Integer> i21 = i25.setLeftChild(new BinaryNode<>(21));
        BinaryNode<Integer> i28 = i25.setRightChild(new BinaryNode<>(28));
        BinaryNode<Integer> i8 = i12.setLeftChild(new BinaryNode<>(8));
        BinaryNode<Integer> i13 = i12.setRightChild(new BinaryNode<>(13));
        BinaryNode<Integer> i6 = i8.setLeftChild(new BinaryNode<>(6));
        BinaryTree binaryTree = new BinaryTree(i20);

        System.out.println("\nParcours pour l'arbre de l'exercice 2");
        System.out.print("Parcours en largeur: \t\t\t\t");
        BinaryTree.widthCourse(binaryTree.getRoot());
        System.out.print("\nParcours en profondeur prefixe: \t");
        System.out.println(binaryTree.prefixDepthCourse(binaryTree.getRoot()));
        System.out.print("Parcours en profondeur suffixe: \t");
        System.out.println(binaryTree.postfixDepthCourse(binaryTree.getRoot()));
        System.out.print("Parcours en profondeur infixe: \t\t");
        System.out.println(binaryTree.infixDepthCourse(binaryTree.getRoot()));
    }

    private static void Implementation_TD2() {
        System.out.println("\n\n[TD2] Implementation du tri par tas (HeapSort)");

        int firstArray[] = {25, 40, 2, 65, 10};
        int secondArray[] = {10, 50, 1, 25, 35, 15};

        System.out.print("Tableau avant le tri par tas: \t");
        HeapSort.print(firstArray);
        HeapSort.sort(firstArray);

        System.out.print("Tableau après le tri par tas: \t");
        HeapSort.print(firstArray);

        System.out.print("\nTableau avant le tri par tas: \t");
        HeapSort.print(secondArray);
        HeapSort.sort(secondArray);

        System.out.print("Tableau après le tri par tas: \t");
        HeapSort.print(secondArray);
    }

    private static void Implementation_TD3_Exo1() {
        System.out.println("\n\n[TD3] Implementation de l'Exercice 1");
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(new BinarySearchNode(20));
        binarySearchTree.insert(new BinarySearchNode(5));
        binarySearchTree.insert(new BinarySearchNode(25));
        binarySearchTree.insert(new BinarySearchNode(3));
        binarySearchTree.insert(new BinarySearchNode(12));
        binarySearchTree.insert(new BinarySearchNode(21));
        binarySearchTree.insert(new BinarySearchNode(28));
        binarySearchTree.insert(new BinarySearchNode(8));
        binarySearchTree.insert(new BinarySearchNode(13));
        binarySearchTree.insert(new BinarySearchNode(6));

        System.out.println("Arbre binaire de recherche initial");
        BinarySearchTree.print(binarySearchTree.getRoot(), "-");

        binarySearchTree.insert(new BinarySearchNode(50));
        binarySearchTree.insert(new BinarySearchNode(5));
        binarySearchTree.insert(new BinarySearchNode(25));
        binarySearchTree.insert(new BinarySearchNode(19));


        System.out.println("\nArbre binaire de recherche après insertion");
        BinarySearchTree.print(binarySearchTree.getRoot(), "-");

        System.out.println("Parcours en profondeur infixe: \t" + binarySearchTree.infixDepthCourse(binarySearchTree.getRoot()));
    }

    private static void Implementation_TD3_Exo2() {
        System.out.println("\n\n[TD3] Implementation de l'Exercice 2");

        final int MAXBOUND = 10000;
        List<Integer> array = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < MAXBOUND; i++) {
            array.add(rand.nextInt(MAXBOUND));
        }
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.listToTree(array);

        long startBinarySearchTree = System.nanoTime();
        binarySearchTree.infixDepthCourse(binarySearchTree.getRoot());
        long timeElapsedBinarySearchTree = System.nanoTime() - startBinarySearchTree;

        long start = System.nanoTime();
        Collections.sort(array);
        long timeElapsed = System.nanoTime() - start;

        System.out.println("Temps pour trier un arbre binaire de recherche: \t \t" + timeElapsedBinarySearchTree);
        System.out.println("Temps pour trier un tableau avec Collections.sort(): \t" + timeElapsed);
    }

    private static void Implementation_TD4_Exo1() {
        System.out.println("\n\n[TD4] Implementation de l'Exercice 1");

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(new BinarySearchNode(20));
        binarySearchTree.insert(new BinarySearchNode(5));
        binarySearchTree.insert(new BinarySearchNode(25));
        binarySearchTree.insert(new BinarySearchNode(3));
        binarySearchTree.insert(new BinarySearchNode(12));
        binarySearchTree.insert(new BinarySearchNode(21));
        binarySearchTree.insert(new BinarySearchNode(28));
        binarySearchTree.insert(new BinarySearchNode(8));
        binarySearchTree.insert(new BinarySearchNode(13));
        binarySearchTree.insert(new BinarySearchNode(6));

        System.out.println("Hauteur de la racine de l'arbre binaire de recherche: " + BinarySearchNode.getHeight(binarySearchTree.getRoot()));
        System.out.println("Facteur d'equilibrage de la racine de l'arbre binaire de recherche: " + BinarySearchNode.balanceFactor(binarySearchTree.getRoot()));
        System.out.println("Cet arbre est un arbre equilibre ? " + binarySearchTree.isBalanceTree());
    }

    private static void Implementation_TD4_Exo2() {
        System.out.println("\n\n[TD4] Implementation de l'Exercice 2");

        List<Integer> array = new ArrayList<>();
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        array = Arrays.asList(10, 5, 12, 2, 7, 15, 4, 17);
        binarySearchTree.listToTree(array);
        BinarySearchTree.print(binarySearchTree.getRoot().getRightChild(), "-");
        System.out.println("Facteur d'equilibrage du fils droit de la racine avant rotation simple gauche: " + BinarySearchNode.balanceFactor(binarySearchTree.getRoot().getRightChild()) + "\n");

        BinarySearchTree.print(BinarySearchNode.simpleLeftRotate(binarySearchTree.getRoot().getRightChild()), "-");
        System.out.println("Facteur d'equilibrage du fils droit de la racine après rotation simple gauche: " + BinarySearchNode.balanceFactor(BinarySearchNode.simpleLeftRotate(binarySearchTree.getRoot().getRightChild())));
    }
}