package com.madjava.stack;

import java.util.Stack;

/**
 * evaluate-reverse-polish-notation
 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are+,-,*,/. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0;i < tokens.length;i++){
            int num = 0;
            try{
                //若出现异常说明是运算符
                num = Integer.parseInt(tokens[i]);
                stack.push(num);
            }catch(Exception e){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(caculate(a,b,tokens[i]));
            }
        }
        return stack.pop();
    }
    public int caculate(int a,int b,String operator){
        int num = 0;
        switch(operator){
            case "+":
                num = a+b;
                break;
            case "-":
                num = a-b;
                break;
            case "*":
                num = a*b;
                break;
            case "/":
                num = a/b;
                break;
        }
        return num;
    }
}