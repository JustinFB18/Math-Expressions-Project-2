package ExpressionTree;

public class TreeNode<T> {
    T element;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T element) {
        this(element, null, null);
    }

    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}