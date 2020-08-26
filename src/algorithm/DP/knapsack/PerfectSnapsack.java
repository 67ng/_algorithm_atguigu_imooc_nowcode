package algorithm.DP.knapsack;

/**
 * @Description: 完全背包问题
 * <p>
 * N件物品，容量为 V 的背包。第i件物品的重量为Wi，价值为Vi，每件物品有无数个。求背包可装物品的最大价值。
 * @Author: matreeix
 * @Date: 2020/8/21
 */

public class PerfectSnapsack {
    public int perfect_knapsack(int[] w, int[] v, int V) {

        int n = w.length;

        int[] f = new int[V + 1];//定义容量为状态转移

        for (int j = 0; j <= V; j++)
            f[j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++)
            for (int j = w[i]; j <= V; j++)//01背包是逆序，完全背包是顺序
                f[j] = Math.max(f[j], v[i] + f[j - w[i]]);

        return f[V];
    }
}
