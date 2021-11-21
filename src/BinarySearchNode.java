public class BinarySearchNode{
    private int data;
    private BinarySearchNode leftChild, rightChild;
    private BinarySearchNode parent = null;

    public BinarySearchNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinarySearchNode getLeftChild() {
        return leftChild;
    }

    public BinarySearchNode setLeftChild(BinarySearchNode leftChild) {
        this.leftChild = leftChild;
        return this.leftChild;
    }

    public BinarySearchNode getRightChild() {
        return rightChild;
    }

    public BinarySearchNode setRightChild(BinarySearchNode rightChild) {
        this.rightChild = rightChild;
        return this.rightChild;
    }

    public BinarySearchNode getParent() {
        return parent;
    }

    public void setParent(BinarySearchNode parent) {
        this.parent = parent;
    }

}
