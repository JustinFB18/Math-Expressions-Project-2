package ExpressionTree;

public class Tree {
    public String expression = "";
    public TreeNode root;

    public Tree() {
        this.root = null;
    }

    boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')') {
            return true;
        }
        return false;
    }

    void postOrder(TreeNode node){
        if (node == null){
            return;
        }
        else{
            postOrder(node.left);
            System.out.println(expression += node.element);
            postOrder(node.right);
        }
    }

    TreeNode constructTree(char[] postfix){
        Stack<TreeNode> arbol = new Stack<TreeNode>();
        TreeNode root,left_child, right_child;

        for(int i = 0;i< postfix.length;i++){
            if (!isOperator(postfix[i])) {
                root = new TreeNode(postfix[i]);
                arbol.push(root);
            } else {
                root = new TreeNode(postfix[i]);
                right_child = arbol.pop();
                root.right = right_child;
                left_child = arbol.pop();
                root.left = left_child;
                arbol.push(root);
            }
        }
        root = arbol.peek();
        arbol.pop();
        return root;
        //return null;
    }

    public static void main(String[] args) {
        Tree miArbol = new Tree();
        String postfix = "ab*";
        char[] charArray = postfix.toCharArray();
        TreeNode root = miArbol.constructTree(charArray);
        miArbol.postOrder(root);
    }
}
