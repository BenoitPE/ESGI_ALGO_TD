package Models.Tree;

import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTree {
    private MyBinarySearchNode root;
    private List<Integer> infixDepthCourseList;
    private List<Integer> postfixDepthCourseList;
    private List<Integer> prefixDepthCourseList;

    //region Constructors
    public MyBinarySearchTree(MyBinarySearchNode root) {
        this.root = root;
        this.infixDepthCourseList =  new ArrayList<Integer>();
    }

    public MyBinarySearchTree() {
        this.root = null;
        this.infixDepthCourseList =  new ArrayList<Integer>();
    }
    //endregion Constructors

    //region Depth Courses
    //Infix depth course for a Binary Search Models.Tree
    public List<Integer> infixDepthCourse(MyBinarySearchNode node) {
        infixDepthCourseList = new ArrayList<Integer>();
        infixDepthCourseRecursive(node);
        return infixDepthCourseList;
    }

    public void infixDepthCourseRecursive(MyBinarySearchNode node) {
        if(node.getLeftChild() != null) {
            infixDepthCourseRecursive(node.getLeftChild());
        }
        infixDepthCourseList.add(node.getData());

        if(node.getRightChild() != null) {
            infixDepthCourseRecursive(node.getRightChild());
        }
    }

    //Prefix depth course for a Binary Search Models.Tree
    public void prefixDepthCourse(MyBinarySearchNode node) {
        prefixDepthCourseList = new ArrayList<Integer>();
        prefixDepthCourseListRecursive(node);
    }

    public void prefixDepthCourseListRecursive(MyBinarySearchNode node) {
        prefixDepthCourseList.add(node.getData());

        if(node.getLeftChild() != null) {
            prefixDepthCourseListRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            prefixDepthCourseListRecursive(node.getRightChild());
        }
    }

    //Postfix depth course for a Binary Search Models.Tree
    public void postfixDepthCourse(MyBinarySearchNode node) {
        postfixDepthCourseList = new ArrayList<Integer>();
        postfixDepthCourseRecursive(node);
    }

    public void postfixDepthCourseRecursive(MyBinarySearchNode node) {
        if(node.getLeftChild() != null) {
            postfixDepthCourseRecursive(node.getLeftChild());
        }
        if(node.getRightChild() != null) {
            postfixDepthCourseRecursive(node.getRightChild());
        }
        postfixDepthCourseList.add(node.getData());
    }
    //endregion Depth Courses

    public boolean search(MyBinarySearchNode rootNode, MyBinarySearchNode key) {
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

    void insert(MyBinarySearchNode key)  {
        root = insertRecursive(root, key);
    }

    MyBinarySearchNode insertRecursive(MyBinarySearchNode rootNode, MyBinarySearchNode key) {
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
            insert(new MyBinarySearchNode(listNode.get(i)));
        }
    }

    public boolean isBalanceTree() {
        if(-1 >= root.balanceFactor(root) && root.balanceFactor(root) <= 1)
            return true;
        else
            return false;
    }

    public static void print(MyBinarySearchNode myBinarySearchNode, String appender) {
        System.out.println(appender + myBinarySearchNode.getData());
        if(myBinarySearchNode.getLeftChild() != null) {
            print(myBinarySearchNode.getLeftChild(), appender + appender);
        }

        if(myBinarySearchNode.getRightChild() != null) {
            print(myBinarySearchNode.getRightChild(), appender + appender);
        }
    }

    //region Getters and Setters
    public MyBinarySearchNode getRoot() {
        return root;
    }

    public void setRoot(MyBinarySearchNode root) {
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
    //endregion Getters and Setters
}
