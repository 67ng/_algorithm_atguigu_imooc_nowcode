package algorithm.DP.knapsack;

/**
 * @Description: 01背包问题
 * <p>
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 * @Author: matreeix
 * @Date: 2020/8/25
 */

public class _01Knapsack {
    //时间复杂度: O(N * V)
    // 空间复杂度: O(V), 只使用了v的额外空间
    public static int _01Snapsack(int[] v, int[] w, int V) {
         int n = w.length;
         int[] f = new int[V+1];

//        for(int j = 0 ; j <= V ; j ++)
//            f[j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i <= n; ++i) {
            for (int j = V; j >= w[i]; --j)
                    f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
        }
        return f[V];
    }
}
