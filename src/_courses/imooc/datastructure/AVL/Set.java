package _courses.imooc.datastructure.AVL;

/**
 * Description:
 *
 * @date: 2018/11/29 17:11
 */
public interface Set<E> {

    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}

