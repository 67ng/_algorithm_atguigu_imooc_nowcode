package simple_fun.data_structure.linear.stack;

/**
 * @Description:
 * @Author: 67ng
 * @Date: 2020/2/23
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
