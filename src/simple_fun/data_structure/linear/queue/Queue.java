package simple_fun.data_structure.linear.queue;

/**
 * @Description:
 * @Author: matreeix
 * @Date: 2020/2/23
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
