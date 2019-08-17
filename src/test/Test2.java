package test;

import java.util.*;

/**
 * Description:测试优先级队列的性质
 * <p>
 * Java的优先队列是小根堆(堆顶的元素为最小元素)，是根据自然排序来进行优先级的判断，
 * 所以自定义的类想要加进优先队列中必须先实现Comparable接口，编写compareTo的方法，方可以使用！
 *
 * @author: NULL
 * @date: 2018/11/6 9:37
 */
public class Test2 {
    public static class PriorotyQueueDemo implements Comparable<PriorotyQueueDemo> {
        int num;

        public PriorotyQueueDemo(int a) {
            this.num = a;
        }

        @Override
        public int compareTo(PriorotyQueueDemo b) {
            return b.num - num;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<PriorotyQueueDemo> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new PriorotyQueueDemo(1));
        priorityQueue.offer(new PriorotyQueueDemo(6));
        priorityQueue.offer(new PriorotyQueueDemo(2));
        priorityQueue.offer(new PriorotyQueueDemo(7));
        priorityQueue.offer(new PriorotyQueueDemo(9));
        priorityQueue.offer(new PriorotyQueueDemo(4));
        priorityQueue.offer(new PriorotyQueueDemo(8));
        priorityQueue.offer(new PriorotyQueueDemo(3));
        priorityQueue.offer(new PriorotyQueueDemo(5));

        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        System.out.println(priorityQueue.poll().num);
        //Collections的shuffle方法
       /* List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        System.out.println(list.toString());*/

    }
}
