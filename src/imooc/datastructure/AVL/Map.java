package imooc.datastructure.AVL;

/**
 * Description:
 *
 * @date: 2018/11/29 17:10
 */
public interface Map<K, V> {

    void add(K key, V value);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    V remove(K key);
    int getSize();
    boolean isEmpty();
}

