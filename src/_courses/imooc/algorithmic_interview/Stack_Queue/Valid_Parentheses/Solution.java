package _courses.imooc.algorithmic_interview.Stack_Queue.Valid_Parentheses;

import java.util.Stack;

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/description/
// 时间复杂度: O(n)
// 空间复杂度: O(n)
public class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for( int i = 0 ; i < s.length() ; i ++ )
            if( s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));//将左括号压入栈
            else{

                if( stack.size() == 0 )
                    return false;

                Character c = stack.pop();

                Character match;//匹配左右括号
                if( s.charAt(i) == ')' )
                    match = '(';
                else if( s.charAt(i) == ']' )
                    match = '[';
                else{
                    assert s.charAt(i) == '}';
                    match = '{';
                }

                if(c != match)//最后栈必须空
                    return false;
            }

        if( stack.size() != 0 )
            return false;

        return true;
    }

    private static void printBool(boolean b){
        System.out.println(b ? "True" : "False");
    }

    public static void main(String[] args) {

        printBool((new Solution()).isValid("()"));
        printBool((new Solution()).isValid("()[]{}"));
        printBool((new Solution()).isValid("(]"));
        printBool((new Solution()).isValid("([)]"));
    }
}
