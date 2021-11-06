package ExpressionTree;

/**
     * Class for the tree node in charge of getting and reading the elements of the tree
     */

public class TreeNode<T> {
    T element;
    TreeNode<T> left;
    TreeNode<T> right; // we set the variables

    public TreeNode(T element) {
        this(element, null, null);
    }

    public TreeNode(T element, TreeNode<T> left, TreeNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public T getElement() { // we start on the first element
        return element;
    }

    public TreeNode<T> getLeft() { // if we get on the left it gets the left
        return left;
    }

    public TreeNode<T> getRight() { // if we get on the right it gets the right
        return right;
    }

    public void setElement(T element) { // we set the elements on the left or right
        this.element = element;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
