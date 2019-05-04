package com.company;

import java.util.List;
import java.util.Stack;

class Evaluate {
    static Double evaluate(List<String> RPN){
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
}
