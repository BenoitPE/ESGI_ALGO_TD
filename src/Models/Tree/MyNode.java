package Models.Tree;

import java.util.ArrayList;
import java.util.List;

public class MyNode<T> {

    private T data = null;

    private List<MyNode<T>> children = new ArrayList<>();

    private MyNode<T> parent = null;

    public MyNode(T data) {
        this.data = data;
    }

    public MyNode<T> addChild(MyNode<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<MyNode<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<MyNode<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(MyNode<T> parent) {
        this.parent = parent;
    }

    public MyNode<T> getParent() {
        return parent;
    }

}