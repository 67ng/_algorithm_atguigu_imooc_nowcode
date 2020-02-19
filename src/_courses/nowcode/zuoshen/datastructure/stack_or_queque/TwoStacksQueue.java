package _courses.nowcode.zuoshen.datastructure.stack_or_queque;

import java.util.Stack;

/**
 * Description:两个栈实现队列
 *
 * @author: 黑山老妖
 * @date: 2018/10/14 13:11
 */
public class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue(){
        stackPush = new Stack<Integer>();
        stackPop = new Stack<Integer>();
    }

    public void push(int pushInt){
        stackPush.push(pushInt);
        dao();
    }

    public int poll(){
        if (stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty!");
        }
        dao();
        return stackPop.pop();
    }

    public int peek(){
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        dao();
        return stackPop.peek();
    }

    public  void dao(){
        if (!stackPop.isEmpty()){
            return;
        }
        while (!stackPush.isEmpty()){
            stackPop.push(stackPush.pop());
        }
    }
}
