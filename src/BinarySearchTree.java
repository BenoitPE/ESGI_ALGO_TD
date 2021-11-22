import java.util.List;

public class BinarySearchTree {
    private BinarySearchNode root;

    public BinarySearchTree(BinarySearchNode root) {
        this.root = root;
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchNode getRoot() {
        return root;
    }

    public void setRoot(BinarySearchNode root) {
        this.root = root;
    }

    void insert(BinarySearchNode key)  {
        root = insert_Recursive(root, key);
    }

    BinarySearchNode insert_Recursive(BinarySearchNode root, BinarySearchNode key) {
        //tree is empty
        if (root == null) {
            root = key;
            return root;
        }
        //traverse the tree
        if (key.getData() < root.getData()) //insert in the left subtree
            root.setLeftChild(insert_Recursive(root.getLeftChild(), key));
        else if (key.getData() > root.getData()) //insert in the right subtree
            root.setRightChild(insert_Recursive(root.getRightChild(), key));
        // return pointer
        return root;
    }

    public void print(BinarySearchNode binarySearchNode, String appender) {
        System.out.println(appender + binarySearchNode.getData());
        if(binarySearchNode.getLeftChild() != null) {
            print(binarySearchNode.getLeftChild(), appender + appender);
        }

        if(binarySearchNode.getRightChild() != null) {
            print(binarySearchNode.getRightChild(), appender + appender);
        }
    }

    //Prefix search for a Binary Search Tree
    public void prefixSearch(BinarySearchNode node) {
        System.out.println(node.getData());

        if(node.getLeftChild() != null) {
            infixSearch(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            infixSearch(node.getRightChild());
        }
    }

    //Postfix search for a Binary Search Tree
    public void postfixSearch(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            infixSearch(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            infixSearch(node.getRightChild());
        }

        System.out.println(node.getData());
    }

    //Infix search for a Binary Search Tree
    public void infixSearch(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            infixSearch(node.getLeftChild());
        }
        System.out.println(node.getData());

        if(node.getRightChild() != null) {
            infixSearch(node.getRightChild());
        }
    }

    public void listToTree(List<Integer> listNode) {
        for (int i = 0; i< listNode.size(); i++) {
            insert(new BinarySearchNode(listNode.get(i)));
        }
    }


}
