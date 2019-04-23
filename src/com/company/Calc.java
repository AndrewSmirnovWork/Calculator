package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Calc {

    private static boolean isDelimetr(String input){
        String delimetr = "()+-*/";
        for(int i = 0; i < delimetr.length(); i++){
            if(input.charAt(0) == delimetr.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isOperator(String input){
        String operator = "+-*/";
        for(int i = 0; i < operator.length(); i++){
            if(input.charAt(0) == operator.charAt(i)) return true;
        }
        return false;
    }
    private static List<String> toRPN(String regular){
        Stack<String> stack = new Stack<String>();
        List<String> RPN = new ArrayList<>();
        for(int i = 0; i<regular.length(); i++){
            String element = String.valueOf(regular.charAt(i));
            if(element.equals(" ")) continue;
            if(isDelimetr(element)){
                if(element.equals("(")) stack.push(element);
                else if (element.equals(")")){
                    while(!stack.peek().equals("(")){
                        RPN.add(stack.pop());
                        if(stack.isEmpty()){
                            System.out.println("Wrong expression, Bracket is not open");
                            return RPN;
                        }

                    }
                    stack.pop();
                }
                else stack.push(element);
            }
            else RPN.add(element);
        }
        while(!stack.isEmpty()){
            if(isOperator(stack.peek())) RPN.add(stack.pop());
            else {
                System.out.println("Bracket is not closed");
                return RPN;
            }
        }
        if(isOperator(RPN.get(RPN.size()-1))) System.out.println("Wrong expression");
        return RPN;
    }

    private static Double evaluate(List<String> RPN){
        Stack<Double> stack = new Stack<>();
        for(String list : RPN) {
            switch (list) {
                case "-": {
                    Double b = (double) stack.pop();
                    Double a = (double) stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/": {
                    Double b = (double) stack.pop();
                    Double a = (double) stack.pop();
                    stack.push(a / b);
                    break;
                }
                default:
                    stack.push(Double.valueOf(list));
                    break;
            }
        }
        return stack.pop();
    }


    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.nextLine();
        //Calc proba = new Calc();
        List<String> newlist = toRPN(s);
        for(String x : newlist) System.out.print(x+" ");
        System.out.println();
        System.out.println(evaluate(newlist));
    }

}
