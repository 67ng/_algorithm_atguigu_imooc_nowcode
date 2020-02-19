package _courses.imooc.datastructure.union_find;

/**
 * Description:
 *
 * @date: 2018/11/28 22:05
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
