package data_structure.linear.ST;

/**
 * @Description: 稀疏表（Sparse Table，也称为ST表）是一种用于解决区间查询问题的数据结构，特别是范围最小查询（RMQ）问题。稀疏表的核心思想是利用动态规划预处理数组，以便在O(1)时间内回答每个查询。
 * @Date: 2024/8/4
 */

public class SparseTable {
    private int[][] st; // 稀疏表
    private int[] arr; // 原始数组
    private int n; // 数组长度

    public SparseTable(int[] array) {
        n = array.length;
        arr = array;
        int logN = (int) (Math.log(n) / Math.log(2)) + 1; // 计算最大层数
        st = new int[n][logN]; // 初始化稀疏表

        // 预处理稀疏表
        for (int i = 0; i < n; i++) {
            st[i][0] = arr[i]; // 稀疏表的第一层就是原始数组
        }

        for (int j = 1; (1 << j) <= n; j++) { // 从第二层开始逐层计算
            for (int i = 0; (i + (1 << j)) <= n; i++) {
                // 利用动态规划的思想，通过上一层的结果计算当前层的结果
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // 查询区间[l, r]的最小值
    public int query(int l, int r) {
        int k = (int) (Math.log(r - l + 1) / Math.log(2)); // 计算查询区间长度对应的层数
        // 返回查询区间对应的两个子区间的最小值的较小者
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        SparseTable st = new SparseTable(arr);
        // 测试查询
        System.out.println("The minimum element in range [1, 5] is " + st.query(1, 5)); // 输出应为0
        System.out.println("The minimum element in range [4, 8] is " + st.query(4, 8)); // 输出应为3
    }
}
