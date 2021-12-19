public class BinarySearchNode {
    private int data;
    private BinarySearchNode leftChild, rightChild;
    private BinarySearchNode parent = null;

    //region Constructors
    public BinarySearchNode(int data) {
        this.data = data;
    }

    public BinarySearchNode(BinarySearchNode another) {
        this.data = another.data;
        this.leftChild = another.leftChild;
        this.rightChild = another.rightChild;
        this.parent = another.parent;
    }

    public BinarySearchNode(int data, BinarySearchNode leftChild, BinarySearchNode rightChild, BinarySearchNode parent) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }
    //endregion Constructors

    public static BinarySearchNode simpleLeftRotate(BinarySearchNode node) {
        BinarySearchNode new_parent = new BinarySearchNode(node.rightChild);
        new_parent.parent = node.parent;
        BinarySearchNode left = new BinarySearchNode(
                node.data,
                node.leftChild,
                new_parent.leftChild,
                new_parent);
        new_parent.leftChild = left;
        return new_parent;
    }

    public static int balanceFactor(BinarySearchNode node) {
        return getHeight(node.getLeftChild()) - getHeight(node.getRightChild());
    }

    public static int getHeight(BinarySearchNode node) {
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

    public BinarySearchNode getLeftChild() {
        return leftChild;
    }

    public BinarySearchNode setLeftChild(BinarySearchNode leftChild) {
        leftChild.parent = this;
        this.leftChild = leftChild;
        return this.leftChild;
    }

    public BinarySearchNode getRightChild() {
        return rightChild;
    }

    public BinarySearchNode setRightChild(BinarySearchNode rightChild) {
        rightChild.parent = this;
        this.rightChild = rightChild;
        return this.rightChild;
    }

    public BinarySearchNode getParent() {
        return parent;
    }

    public void setParent(BinarySearchNode parent) {
        this.parent = parent;
    }
    //endregion Getters and Setters

}
