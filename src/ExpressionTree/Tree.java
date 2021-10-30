package ExpressionTree;

import java.util.Stack;

public class Tree {
    public String expression = "";
    public TreeNode root;
    public double result = 1;
    public double leftNumber = 0;
    public double rightNumber = 0;
    public String operator = "";


    public Tree() {
        this.root = null;
    }


    void postOrder(TreeNode node){
        if (node == null){
            return;
        }
        else{
            postOrder(node.left);
            postOrder(node.right);
            //System.out.println(expression += node.element);
            System.out.println(node.element);
        }
    }

    public double evaluateTree(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right==null){
            return Double.valueOf((String) root.element);
        }

        leftNumber = evaluateTree(root.left);
        rightNumber = evaluateTree(root.right);
        operator = (String) root.element;

        if (operator.equals("+"))
            return leftNumber + rightNumber;

        if (operator.equals("-"))
            return leftNumber - rightNumber;

        if (operator.equals("*"))
            return leftNumber * rightNumber;

        return leftNumber / rightNumber;
        /*switch (operator){
            case "+":
                result = leftNumber + rightNumber; break;
            case "-":
                result = leftNumber - rightNumber; break;
            case "*":
                result = leftNumber * rightNumber; break;
            case "/":
                result = leftNumber / rightNumber; break;
            case "%":
                result = leftNumber % rightNumber; break;
        }
        return result;*/
    }

    TreeNode constructTree(String[] postfix){
        Stack<TreeNode> myTree = new Stack<TreeNode>();
        TreeNode left_child, right_child;
        Boolean firstNumber = false;
        String operator = "+-*/%";

        for(int i = 0;i< postfix.length;i++){
            if (operator.contains(postfix[i])) {
                right_child = myTree.pop();
                left_child = myTree.pop();
                TreeNode root = new TreeNode(postfix[i],left_child,right_child);
                myTree.push(root);
                firstNumber = false;
            }else if(!firstNumber){
                TreeNode leftNumber = new TreeNode(postfix[i]);
                myTree.push(leftNumber);
                firstNumber = true;
            } else if(firstNumber){
                TreeNode rightNumber = new TreeNode(postfix[i]);
                myTree.push(rightNumber);
            } else {
                myTree.push(new TreeNode(postfix[i]));
            }
        }
        root = myTree.pop();
        System.out.println(root.element);
        System.out.println(root.left.element);
        System.out.println(root.right.element);
        return root;
    }

    public static void main(String[] args) {
        Tree miArbol = new Tree();
        //String postfix = "6,2,+,3,2,/,*,4,2,%,-";
        String postfix = "5,4,*,100,20,-,+";
        String[] postArray = postfix.split(",");
        TreeNode root = miArbol.constructTree(postArray);
        //miArbol.postOrder(root);
        System.out.println("El resultado es: "+ miArbol.evaluateTree(root));
    }
}
