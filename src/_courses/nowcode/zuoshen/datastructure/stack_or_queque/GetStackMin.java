package _courses.nowcode.zuoshen.datastructure.stack_or_queque;

import java.util.Stack;

/**
 * Description:实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 *
 * @author: 黑山老妖
 * @date: 2018/10/14 11:50
 */
public class GetStackMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetStackMin() {
        this.stackData = new Stack<Integer>();
        this.stackMin = new Stack<Integer>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum < this.getMin()) {
            this.stackMin.push(newNum);
        } else {
            int newMin = this.stackMin.peek();
            this.stackMin.push(newMin);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        return this.stackMin.peek();
    }
}
