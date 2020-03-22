package simple_fun.data_structure.advanced.union_find;

/**
 * Description: 并查集的接口
 *
 * @date: 2018/11/28 22:05
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
