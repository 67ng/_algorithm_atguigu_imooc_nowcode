package _courses.imooc.datastructure.Heap_PriorityQueue;

/**
 * Description:队列接口
 *
 * @date: 2018/11/27 18:41
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}

