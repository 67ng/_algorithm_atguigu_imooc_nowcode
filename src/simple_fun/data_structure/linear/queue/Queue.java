package simple_fun.data_structure.linear.queue;

/**
 * @Description:
 * @Author: 67ng
 * @Date: 2020/2/23
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
