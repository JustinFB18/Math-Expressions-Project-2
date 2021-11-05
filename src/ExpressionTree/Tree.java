package ExpressionTree;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;


/**
 * Binary Expression Tree Class that construct a tree according to a postfix expression and
 * evaluate a tree.
 *
 * @author Justin Fern&aacute;ndez y Abraham Venegas
 * @version 1
 */
public class Tree {
    public String expression = "";
    public TreeNode root;
    public String operator = "";


    /**
     * Constructor method that initialize the root of the tree.
     */
    public Tree() {
        this.root = null;
    }


    /**
     * Evaluate the tree in a recursive way, first the left element, after the right element and finally its root.
     *
     * @param root Receives the element in the peek of the tree.
     */
    public double evaluateTree(TreeNode root){
        if (root.element == null)
            return 0;
        if (root.left == null && root.right==null)
            return Double.valueOf((String) root.element);


        Double leftNumber = evaluateTree(root.left);
        Double rightNumber = evaluateTree(root.right);
        operator = (String) root.element;

        switch (operator){
            case "+":
                return leftNumber + rightNumber;
            case "-":
                return leftNumber - rightNumber;
            case "*":
                return leftNumber * rightNumber;
            case "/":
                if (rightNumber == 0){
                    return 0;
                }
                return leftNumber / rightNumber;
        }
        if (rightNumber == 0){
            return 0;
        }
        return leftNumber % rightNumber;
    }

    /**
     * It constructs the Binary Expression Tree according to the postfix array that receives.
     *
     * @param postfix Receives a Array of the postfix expression, in each position it has a element.
     */
    public TreeNode constructTree(String[] postfix){
        Stack<TreeNode> myTree = new Stack<TreeNode>();
        TreeNode left_child, right_child;
        Boolean firstNumber = false;
        String operator = "+-*/%";

        for(int i = 0;i< postfix.length;i++){
            if (operator.contains(postfix[i])) {
                right_child = myTree.pop();
                try {
                    left_child = myTree.pop();
                    if (left_child.element.equals("")){
                        left_child.element =0;
                    }
                }catch (EmptyStackException t){
                    left_child = new TreeNode(null);
                }
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
        return root;
    }
}
