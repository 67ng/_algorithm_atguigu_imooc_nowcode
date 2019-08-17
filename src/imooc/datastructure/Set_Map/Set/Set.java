package imooc.datastructure.Set_Map.Set;

/**
 * Description:
 *
 * @date: 2018/11/26 23:09
 */
public interface Set<E> {

    void add(E e);
    boolean contains(E e);
    void remove(E e);
    int getSize();
    boolean isEmpty();
}

