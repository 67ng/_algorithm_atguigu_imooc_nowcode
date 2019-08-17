package nowcode.zuoshen.datastructure.stack_or_queque;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:队列实现栈
 *
 * @author: 黑山老妖
 * @date: 2018/10/14 12:41
 */
public class TwoQueuesStack {
    private Queue<Integer> data;
    private Queue<Integer> help;

    public TwoQueuesStack() {
        data = new LinkedList<Integer>();
        help = new LinkedList<Integer>();
    }

    public void push(int pushInt) {
        data.add(pushInt);
    }

    public int peek() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (data.size() != 1) {
            help.add(data.poll());
        }
        int res = data.poll();
        help.add(res);
        swap();
        return res;
    }

    public int pop() {
        if (data.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        while (data.size() > 1) {
            help.add(data.poll());
        }
        int res = data.poll();
        swap();
        return res;
    }

    private void swap() {
        Queue<Integer> tmp = help;
        help = data;
        data = tmp;
    }
}













