package Models.Tree;

public class MyBinarySearchNode {
    private int data;
    private MyBinarySearchNode leftChild, rightChild;
    private MyBinarySearchNode parent = null;

    //region Constructors
    public MyBinarySearchNode(int data) {
        this.data = data;
    }

    public MyBinarySearchNode(MyBinarySearchNode another) {
        this.data = another.data;
        this.leftChild = another.leftChild;
        this.rightChild = another.rightChild;
        this.parent = another.parent;
    }

    public MyBinarySearchNode(int data, MyBinarySearchNode leftChild, MyBinarySearchNode rightChild, MyBinarySearchNode parent) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }
    //endregion Constructors

    public static MyBinarySearchNode simpleLeftRotate(MyBinarySearchNode node) {
        MyBinarySearchNode new_parent = new MyBinarySearchNode(node.rightChild);
        new_parent.parent = node.parent;
        MyBinarySearchNode left = new MyBinarySearchNode(
                node.data,
                node.leftChild,
                new_parent.leftChild,
                new_parent);
        new_parent.leftChild = left;
        return new_parent;
    }

    public static int balanceFactor(MyBinarySearchNode node) {
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    public static int getHeight(MyBinarySearchNode node) {
        if (node == null)
            return 0;
        else {
            return Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())) + 1;
        }
    }

    //region Getters and Setters
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public MyBinarySearchNode getLeftChild() {
        return leftChild;
    }

    public MyBinarySearchNode setLeftChild(MyBinarySearchNode leftChild) {
        leftChild.parent = this;
        this.leftChild = leftChild;
        return this.leftChild;
    }

    public MyBinarySearchNode getRightChild() {
        return rightChild;
    }

    public MyBinarySearchNode setRightChild(MyBinarySearchNode rightChild) {
        rightChild.parent = this;
        this.rightChild = rightChild;
        return this.rightChild;
    }

    public MyBinarySearchNode getParent() {
        return parent;
    }

    public void setParent(MyBinarySearchNode parent) {
        this.parent = parent;
    }
    //endregion Getters and Setters

}
