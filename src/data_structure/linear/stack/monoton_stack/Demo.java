package data_structure.linear.stack.monoton_stack;


import java.util.Stack;

/**
 * @Description: 单调栈的模板
 * @Author: matreeix
 * @Date: 2020/7/29
 */

public class Demo {
    //单调递增栈
    public static void monoStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && stack.peek() > arr[i]) {
                stack.pop();
            }
            stack.push(arr[i]);
        }
    }

}
