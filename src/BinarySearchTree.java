import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private BinarySearchNode root;
    private List<Integer> infixDepthCourseList;
    private List<Integer> postfixDepthCourseList;
    private List<Integer> prefixDepthCourseList;

    //region Constructors
    public BinarySearchTree(BinarySearchNode root) {
        this.root = root;
        this.infixDepthCourseList =  new ArrayList<Integer>();
    }

    public BinarySearchTree() {
        this.root = null;
        this.infixDepthCourseList =  new ArrayList<Integer>();
    }
    //endregion Constructors

    //region Depth Courses
    //Infix depth course for a Binary Search Tree
    public void infixDepthCourse(BinarySearchNode node) {
        infixDepthCourseList = new ArrayList<Integer>();
        infixDepthCourseRecursive(node);
    }

    public void infixDepthCourseRecursive(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            infixDepthCourseRecursive(node.getLeftChild());
        }
        infixDepthCourseList.add(node.getData());

        if(node.getRightChild() != null) {
            infixDepthCourseRecursive(node.getRightChild());
        }
    }

    //Prefix depth course for a Binary Search Tree
    public void prefixDepthCourse(BinarySearchNode node) {
        prefixDepthCourseList = new ArrayList<Integer>();
        prefixDepthCourseListRecursive(node);
    }

    public void prefixDepthCourseListRecursive(BinarySearchNode node) {
        prefixDepthCourseList = new ArrayList<Integer>();

        if(node.getLeftChild() != null) {
            prefixDepthCourseListRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            prefixDepthCourseListRecursive(node.getRightChild());
        }
    }

    //Postfix depth course for a Binary Search Tree
    public void postfixDepthCourse(BinarySearchNode node) {
        postfixDepthCourseList = new ArrayList<Integer>();
        postfixDepthCourseRecursive(node);
    }

    public void postfixDepthCourseRecursive(BinarySearchNode node) {
        if(node.getLeftChild() != null) {
            postfixDepthCourseRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            postfixDepthCourseRecursive(node.getRightChild());
        }
        postfixDepthCourseList.add(node.getData());
    }
    //endregion Depth Courses

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
        root = insertRecursive(root, key);
    }

    BinarySearchNode insertRecursive(BinarySearchNode rootNode, BinarySearchNode key) {
        //tree is empty
        if (rootNode == null) {
            rootNode = key;
            return rootNode;
        }
        //traverse the tree
        if (key.getData() < rootNode.getData()) //insert in the left subtree
            rootNode.setLeftChild(insertRecursive(rootNode.getLeftChild(), key));
        else if (key.getData() > rootNode.getData()) //insert in the right subtree
            rootNode.setRightChild(insertRecursive(rootNode.getRightChild(), key));
        // return pointer
        return rootNode;
    }

    public void listToTree(List<Integer> listNode) {
        for (int i = 0; i< listNode.size(); i++) {
            insert(new BinarySearchNode(listNode.get(i)));
        }
    }

    public boolean isBalanceTree() {
        if(-1 >= root.balanceFactor(root) && root.balanceFactor(root) <= 1)
            return true;
        else
            return false;
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

    //region getters and setters
    public BinarySearchNode getRoot() {
        return root;
    }

    public void setRoot(BinarySearchNode root) {
        this.root = root;
    }

    public List<Integer> getInfixDepthCourseList() {
        return infixDepthCourseList;
    }

    public void setInfixDepthCourseList(List<Integer> infixDepthCourseList) {
        this.infixDepthCourseList = infixDepthCourseList;
    }

    public List<Integer> getPostfixDepthCourseList() {
        return postfixDepthCourseList;
    }

    public void setPostfixDepthCourseList(List<Integer> postfixDepthCourseList) {
        this.postfixDepthCourseList = postfixDepthCourseList;
    }

    public List<Integer> getPrefixDepthCourseList() {
        return prefixDepthCourseList;
    }

    public void setPrefixDepthCourseList(List<Integer> prefixDepthCourseList) {
        this.prefixDepthCourseList = prefixDepthCourseList;
    }
    //endregion getters and setters
}
