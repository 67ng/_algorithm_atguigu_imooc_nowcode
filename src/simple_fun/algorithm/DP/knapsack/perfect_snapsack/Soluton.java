package simple_fun.algorithm.DP.knapsack.perfect_snapsack;

/**
 * @Description: 完全背包
 * <p>
 * N件物品，容量为W的背包。第i件物品的重量为Wi，价值为Vi，每件物品有无数个。求背包可装物品的最大价值。
 *
 *
 * @Author: 67ng
 * @Date: 2020/4/6
 */
public class Soluton {

    public int perfect_knapsack(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || C == 0)
            return 0;

        int[] memo = new int[C + 1];//定义容量为状态转移

        for (int j = 0; j <= C; j++)
            memo[j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++)
            for (int j = w[i]; j <= C; j++)//01背包是逆序，完全背包是顺序
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);

        return memo[C];
    }


}
