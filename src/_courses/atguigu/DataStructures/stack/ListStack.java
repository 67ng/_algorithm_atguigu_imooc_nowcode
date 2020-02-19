package _courses.atguigu.DataStructures.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 链表实现栈
 * @Author: 67ng
 * @Date: 2019/8/17 20:33
 */
public class ListStack {

    private int maxSize; // 栈的大小
    private List<Integer> stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top表示栈顶，初始化为-1

    //构造器
    public ListStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new LinkedList<>();
    }

    //栈满
    public  boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack.add(value);
    }

    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack.remove(top);
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.println(pop());
        }
    }


}
