public class BinaryTree {
    private BinaryNode root;

    public BinaryTree(BinaryNode root) {
        this.root = root;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    public <T> void print(BinaryNode<T> binaryNode, String appender) {
        System.out.println(appender + binaryNode.getData());
        if(binaryNode.getLeftChild() != null) {
            print(binaryNode.getLeftChild(), appender + appender);
        }

        if(binaryNode.getRightChild() != null) {
            print(binaryNode.getRightChild(), appender + appender);
        }
    }
}
