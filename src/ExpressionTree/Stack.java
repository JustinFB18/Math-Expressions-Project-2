package ExpressionTree;

public class Stack<T> {
    private LinkedList stackList;

    // Class constructor
    public Stack() {
        stackList = new LinkedList();
    }

    public void push(TreeNode newElement) {
        this.stackList.insertFirst(newElement);
    }

    public TreeNode pop() {
        return this.stackList.deleteFirst();
    }

    public TreeNode peek() {
        return this.stackList.getHead();
    }
}
