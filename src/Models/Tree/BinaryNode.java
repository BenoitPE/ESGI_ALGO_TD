package Models.Tree;

public class BinaryNode<T> {
    private T data;
    private BinaryNode<T> leftChild, rightChild;
    private BinaryNode<T> parent = null;

    public BinaryNode(T data) {
        this.data = data;
    }

    public BinaryNode() {
        this.data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public BinaryNode<T> setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = leftChild;
        return this.leftChild;
    }

    public BinaryNode<T> getRightChild() {
        return rightChild;
    }

    public BinaryNode<T> setRightChild(BinaryNode<T> rightChild) {
        this.rightChild = rightChild;
        return this.rightChild;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

}
