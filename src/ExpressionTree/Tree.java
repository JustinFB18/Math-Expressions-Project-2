package ExpressionTree;

import java.util.Stack;

public class Tree {
    public String expression = "";
    public TreeNode root;

    public Tree() {
        this.root = null;
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '(' || c == ')';
    }

    void postOrder(TreeNode node){
        if (node == null){
            return;
        }
        else{
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(expression += node.element);
            //System.out.println(node.element);
        }
    }

    TreeNode constructTree(char[] postfix){
        Stack<TreeNode> arbol = new Stack<TreeNode>();
        TreeNode left_child, right_child;
        String number1 = "";
        String operador = "";
        String number2 = "";

        for(int i = 0;i< postfix.length;i++){
            if(!isOperator(postfix[i]) && operador.equals("")){
                number1 += postfix[i];
            } else if (isOperator(postfix[i]) && operador.equals("")){
                arbol.push(new TreeNode(number1));
                operador += postfix[i];
            } else if(!isOperator(postfix[i]) && !operador.equals("")){
                number2 += postfix[i];
                if(i+1 == postfix.length){
                    arbol.push(new TreeNode(number2));
                    right_child = arbol.pop();
                    left_child = arbol.pop();
                    TreeNode root = new TreeNode(operador,left_child,right_child);
                    arbol.push(root);
                    System.out.println("root.element+root.left+root.right = " + root.element+root.left.element+root.right.element);
                }
            }else{
                arbol.push(new TreeNode(number2));
                right_child = arbol.pop();
                left_child = arbol.pop();
                TreeNode root = new TreeNode(postfix[i],left_child,right_child);
                arbol.push(root);
                number1= "";
                number2 = "";
                operador = "";
            }

            /*
            if (isOperator(postfix[i])) {
                right_child = arbol.pop();
                left_child = arbol.pop();
                TreeNode root = new TreeNode(postfix[i],left_child,right_child);
                arbol.add(root);
            } else {
                arbol.add(new TreeNode(postfix[i]));
            }*/
        }
        root = arbol.pop();
        System.out.println(root.element);
        return root;
        //return null;
    }

    public static void main(String[] args) {
        Tree miArbol = new Tree();
        String postfix = "12*5+13*2";
        char[] charArray = postfix.toCharArray();
        TreeNode root = miArbol.constructTree(charArray);
        miArbol.postOrder(root);
    }
}
