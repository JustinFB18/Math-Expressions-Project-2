package ExpressionTree;

import java.io.IOException;
import java.util.Stack;


/**
 * This Class transform the expression that client write in infix to postfix form.
 *
 * @author Justin Fern&aacute;ndez y Abraham Venegas
 * @version 1
 */
public class InfixToPostFix {
    private String Operator = "*/%^+-!=";
    private Stack SavedOperators = new Stack();
    public String inFix = "";
    private String PostFix = "";

    /**
     * Return the important precedence of a operator.
     *
     * @param c Receives a character.
     */
    public static int precedence(String c) {
        switch (c) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
            case "^":
                return 2;
        }
        return -1;
    }

    /**
     * Constructor method that initialize the operator, the Stack with the operators of the expression and
     * the infix and postfix strings.
     */
    public InfixToPostFix(){
        this.Operator = Operator;
        this.SavedOperators = SavedOperators;
        this.inFix = inFix;
        this.PostFix = PostFix;
    }

    /**
     * This function saved the operators in a stack and according to three basic mathematic rules transform
     * the string in the infix to postfix form.
     */
    public String transforming(){
        this.SavedOperators.push("(");
        for (int j = 0; j < this.inFix.length(); j=j+1) {
            String character = String.valueOf(this.inFix.charAt(j));

            if (character.equals("(")){
                this.SavedOperators.push(character);
            }else if (!this.Operator.contains(character) && !character.endsWith(")")){
                this.PostFix += character;
            }

            if (this.Operator.contains(character)){
                if (this.SavedOperators.empty()){
                    this.SavedOperators.push(character);
                }else {
                    String Aux = (String) this.SavedOperators.peek();
                    int priorityFirst = precedence(character);
                    int prioritySecond = precedence(Aux);
                    while (priorityFirst < prioritySecond && !Aux.equals("(")) {
                        String temp = (String) this.SavedOperators.pop();
                        System.out.println("temp = " + temp);
                        this.PostFix +=","+ temp;
                        Aux = (String) this.SavedOperators.peek();
                        prioritySecond = this.Operator.indexOf(Aux);
                    }
                    if (priorityFirst == prioritySecond){
                        String Aux2 = (String)this.SavedOperators.pop();
                        if (!Aux2.equals("(")){
                            this.PostFix += ","+ Aux2;
                        }
                    }
                    if (!character.equals("")){
                        this.SavedOperators.push(character);
                        this.PostFix += ",";
                    }
                }
            }

            if (character.equals(")")){
                String Aux = (String) this.SavedOperators.peek();
                while (!Aux.equals("(")){
                    this.PostFix += ","+(String) this.SavedOperators.pop();
                    Aux = (String) this.SavedOperators.peek();
                }
                this.SavedOperators.pop();
            }
        }
        while (!this.SavedOperators.empty()) {
            String comprobation = (String) this.SavedOperators.peek();
            if (!comprobation.equals("(")){
                this.PostFix += ","+(String) this.SavedOperators.pop();
            }else{
                this.SavedOperators.pop();
            }
        }
        return this.PostFix;
    }
}
