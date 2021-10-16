package ExpressionTree;

public class Node {
    public TreeNode data;
    public Node next;

    public Node(TreeNode data) {
        this.next = null;
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(TreeNode data) {
        this.data = data;
    }

    public TreeNode getNext() {
        return this.next.data;
    }

    public void setNext(Node node) {
        this.next = node;
    }
}
