package _courses.imooc.datastructure.stack;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/11/25 11:17
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
