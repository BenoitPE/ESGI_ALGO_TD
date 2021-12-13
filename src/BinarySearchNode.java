public class BinarySearchNode{
    private int data;
    private BinarySearchNode leftChild, rightChild;
    private BinarySearchNode parent = null;

    public BinarySearchNode(int data) {
        this.data = data;
    }

    /*
    if balance factor(top) = 2: // right is imbalanced
         if balance factor(R) = 1: //
              do a left rotation
         else if balance factor(R) = -1:
              do a double rotation
    else: // must be -2, left is imbalanced
         if balance factor(L) = 1: //
              do a left rotation
         else if balance factor(L) = -1:
              do a double rotation
    * */

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

    /*
       height(node):
       if node == null:
            return 0
       else:
            return max(height(node.L), height(node.R)) + 1
    */

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
