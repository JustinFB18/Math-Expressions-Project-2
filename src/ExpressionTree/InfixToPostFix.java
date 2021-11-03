package ExpressionTree;

import java.util.Stack;

public class InfixToPostFix {
    private String Operator = "*/%^+-!=";
    private Stack SavedOperators = new Stack();
    public String inFix = "";
    private String PostFix = "";

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

    public InfixToPostFix(){
        this.Operator = Operator;
        this.SavedOperators = SavedOperators;
        this.inFix = inFix;
        this.PostFix = PostFix;
    }
    public String transforming(){
        this.SavedOperators.push("(");
        for (int j = 0; j < this.inFix.length(); j=j+1) {
            String character = String.valueOf(this.inFix.charAt(j));
            System.out.println("character = " + character);

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
                    this.SavedOperators.push(character);
                    this.PostFix += ",";
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
        System.out.println("La expresión postfija es: "+this.PostFix);
        return this.PostFix;
    }

    public static void main(String[] args) {
        InfixToPostFix o = new InfixToPostFix();
        o.inFix = "(6+2)*(3/2)-(4%2)";
        o.inFix = "(5*4)+(100-20)";
        o.transforming();
        System.out.println("o.PostFix = " + o.PostFix);
    }
}
