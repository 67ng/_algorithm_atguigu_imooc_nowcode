package imooc.datastructure.queue;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/11/25 11:37
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
