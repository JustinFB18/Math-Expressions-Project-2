package ExpressionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;

public class InfixToPostFix {
    private Hashtable<String,Integer> symbolAndPresedence = new Hashtable<>();
    private Stack operators = new Stack();
    private String inFix = "";
    private char[] inFixList = new char[]{};
    private char[] symbols = new char[]{'0','1','2','3','4','5','6','7','8','9'};
    private ArrayList<Character> postFix = new ArrayList<Character>();

    public InfixToPostFix(){
        this.symbolAndPresedence = symbolAndPresedence;
        this.operators = operators;
        this.inFix = inFix;
        this.symbols = symbols;
        this.inFixList = inFix.toCharArray();
        this.postFix = postFix;
    }

    public void Presedence(){
        this.symbolAndPresedence.put("*",3);
        this.symbolAndPresedence.put("/",3);
        this.symbolAndPresedence.put("+",2);
        this.symbolAndPresedence.put("-",2);
        this.symbolAndPresedence.put("(",1);
    }

    public ArrayList<Character> transforming(){
        Presedence();
        for (char value: this.inFixList){
            boolean state = false;
            for (int i = 0; i < this.symbols.length; i++) {
                state = this.symbols[i] == value;
                if (state){
                    break;
                }
            }
            if (state){
                this.postFix.add(value);
            } else if(value == '('){
                this.operators.add(value);
            } else if(value == ')'){
                char symbolPeak = (char) this.operators.pop();
                while(symbolPeak != '('){
                    this.postFix.add(symbolPeak);
                    symbolPeak = (char) this.operators.pop();
                }
            } else{
                while (this.symbols.length != 0 && (this.symbolAndPresedence.get("*") >= this.symbolAndPresedence.get("1"))){
                    this.postFix.add((Character) this.operators.pop());
                }
                this.operators.add(value);
            }
        }

        while(this.operators.size() != 0){
            System.out.println(this.postFix.get(0));
            this.postFix.add((Character) this.operators.pop());
        }
        return this.postFix;
    }

    public static void main(String[] args) {
        InfixToPostFix o = new InfixToPostFix();
        o.inFix = "1*2+3*4";
        o.inFixList = o.inFix.toCharArray();
        ArrayList<Character> respuesta = o.transforming();
        System.out.println(respuesta.toString());
    }
}
