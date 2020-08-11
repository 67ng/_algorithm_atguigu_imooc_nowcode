package simple_fun.data_structure.linear.queue;

import java.util.Stack;

/**
 * @Description: 栈实现队列
 * @Author: caffebaby
 * @Date: 2020/2/23
 */
public class Stacks2Queue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public Stacks2Queue(){
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
