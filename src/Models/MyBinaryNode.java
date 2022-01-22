package Models;

public class MyBinaryNode<T> {
    private T data;
    private MyBinaryNode<T> leftChild, rightChild;
    private MyBinaryNode<T> parent = null;

    public MyBinaryNode(T data) {
        this.data = data;
    }

    public MyBinaryNode() {
        this.data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyBinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public MyBinaryNode<T> setLeftChild(MyBinaryNode<T> leftChild) {
        this.leftChild = leftChild;
        return this.leftChild;
    }

    public MyBinaryNode<T> getRightChild() {
        return rightChild;
    }

    public MyBinaryNode<T> setRightChild(MyBinaryNode<T> rightChild) {
        this.rightChild = rightChild;
        return this.rightChild;
    }

    public MyBinaryNode<T> getParent() {
        return parent;
    }

    public void setParent(MyBinaryNode<T> parent) {
        this.parent = parent;
    }

}
