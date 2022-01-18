package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 二叉索引树
 * 树状数组或二叉索引树（Binary Indexed Tree），又以其发明者命名为 Fenwick 树。
 * 其初衷是解决数据压缩里的累积频率的计算问题，现多用于高效计算数列的前缀和、区间和。
 * 它可以以 O(logn) 的时间执行下面两种操作：
 * 1、数组任意前缀和的查询
 * 2、单点更新
 *空间复杂度 O(n)。
 *
 * @Author: matreeix
 * @Date: 2020/8/21
 */

public class BinaryIndexTree1 {

    private int[] tree;
    private int len;

    public BinaryIndexTree1(int n) {
        this.len = n;
        tree = new int[n + 1];
    }

    /**
     * 单点更新
     * @param i 原始数组索引 i
     * @param delta 变化值 = 更新以后的值 - 原始值
     */
    public void update(int i, int delta) {
        while (i <= len) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    /**
     * 查询前缀和
     * @param i 前缀的最大索引，即查询区间 [0, i] 的所有元素之和
     */
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    /**
     * 求x的最低位1
     * */
    public static int lowbit(int x) {
        return x & (-x);
    }
}
