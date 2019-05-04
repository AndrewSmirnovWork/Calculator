package com.company;
import java.util.*;

public class Calc {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.nextLine();
        //Calc proba = new Calc();
        List<String> newlist = Rpn.toRPN(s);
        for(String x : newlist) System.out.print(x+" ");
        System.out.println();
        System.out.println(Evaluate.evaluate(newlist));
    }

}
