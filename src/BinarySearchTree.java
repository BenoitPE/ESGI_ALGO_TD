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
    public void prefixDepthCourse(BinarySearchNode node) {
        System.out.println(node.getData());

        if(node.getLeftChild() != null) {
            prefixDepthCourse(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            prefixDepthCourse(node.getRightChild());
        }
    }

    //Postfix search for a Binary Search Tree
    public void postfixDepthCourse(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            postfixDepthCourse(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            postfixDepthCourse(node.getRightChild());
        }

        System.out.println(node.getData());
    }

    //Infix search for a Binary Search Tree
    public void infixDepthCourse(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            infixDepthCourse(node.getLeftChild());
        }
        System.out.println(node.getData());

        if(node.getRightChild() != null) {
            infixDepthCourse(node.getRightChild());
        }
    }

    public boolean search(BinarySearchNode rootNode, BinarySearchNode key) {
        if(rootNode == null) {
            return false;
        }
        else {
            if(rootNode.getData() == key.getData()) {
                return true;
            } else if (key.getData() < rootNode.getData()){
                return search(rootNode.getLeftChild(), key);
            } else {
                return search(rootNode.getRightChild(), key);
            }
        }
    }

    void insert(BinarySearchNode key)  {
        root = insert_Recursive(root, key);
    }

    BinarySearchNode insert_Recursive(BinarySearchNode rootNode, BinarySearchNode key) {
        //tree is empty
        if (rootNode == null) {
            rootNode = key;
            return rootNode;
        }
        //traverse the tree
        if (key.getData() < rootNode.getData()) //insert in the left subtree
            rootNode.setLeftChild(insert_Recursive(rootNode.getLeftChild(), key));
        else if (key.getData() > root.getData()) //insert in the right subtree
            rootNode.setRightChild(insert_Recursive(rootNode.getRightChild(), key));
        // return pointer
        return rootNode;
    }

    public void listToTree(List<Integer> listNode) {
        for (int i = 0; i< listNode.size(); i++) {
            insert(new BinarySearchNode(listNode.get(i)));
        }
    }


}
