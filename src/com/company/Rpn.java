package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Rpn {
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

    static List<String> toRPN(String input){
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
}
