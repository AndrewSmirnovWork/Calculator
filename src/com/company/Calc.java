package com.company;


import java.util.*;

public class Calc {
    private static String delimetr = "()+-*/ ";
    private static String operator = "+-*/";

    private static boolean isDelimetr(String input){
        for(int i = 0; i < delimetr.length(); i++){
            if(input.charAt(0) == delimetr.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isOperator(String input){
        for(int i = 0; i < operator.length(); i++){
            if(input.charAt(0) == operator.charAt(i)) return true;
        }
        return false;
    }
    private static int priority(String input) {
        if (input.equals("-") || input.equals("+")) return 0;
        else return 1;
    }

    private static List<String> toRPN(String input){
        StringTokenizer token = new StringTokenizer(input, delimetr, true);
        Stack<String> stack = new Stack<>();
        List<String> RPN = new ArrayList<>();
        while(token.hasMoreTokens()) {
            String strToken = token.nextToken();
            if (strToken.equals(" ")) continue;
            if (isDelimetr(strToken)) {
                if (strToken.equals("(")) stack.push(strToken);
                else if (strToken.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        RPN.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("Wrong expression, Bracket is not open");
                            return RPN;
                        }
                    }
                    stack.pop();
                }
                else if(!stack.isEmpty())
                {
                    if (priority(strToken) >= priority(stack.peek())) {
                    stack.push(strToken);
                } else {
                                 RPN.add(stack.pop());
                                stack.push(strToken);
                        }
                }
                else stack.add(strToken);
            } else RPN.add(strToken);
        }
        while(!stack.isEmpty()){
            if(isOperator(stack.peek())) RPN.add(stack.pop());
            else {
                System.out.println("Bracket is not closed");
                return RPN;
            }
        }
        //if(isOperator(RPN.get(RPN.size()-1))) System.out.println("Wrong expression");
        return RPN;
    }

    private static Double evaluate(List<String> RPN){
        Stack<Double> stack = new Stack<>();
        for(String list : RPN) {
            switch (list) {
                case "-": {
                    Double b = stack.pop();
                    Double a = stack.pop();
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
                    Double b = stack.pop();
                    Double a = stack.pop();
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
