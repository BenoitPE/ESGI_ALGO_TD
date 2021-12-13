public class BinarySearchNode{
    private int data;
    private BinarySearchNode leftChild, rightChild;
    private BinarySearchNode parent = null;

    public BinarySearchNode(int data) {
        this.data = data;
    }

    public int balanceFactor(BinarySearchNode node) {
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    public int height(BinarySearchNode node) {
        if(node == null)
            return 0;
        else {
            return Math.max(height(node.getLeftChild()),height(node.getRightChild())) +1;
        }
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
