package algorithm.DP.tree_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: 887.鸡蛋掉落
 * @Description: 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * @Linked: https://leetcode-cn.com/problems/super-egg-drop/
 */

public class EggDrop {

    //经典的谷歌面试题，现仅介绍一种独特的数学法
//    如果鸡蛋没有碎，那么对应的是 f(T - 1, K)f(T−1,K)，也就是说在这一层的上方可以有 f(T - 1, K)f(T−1,K) 层；
//    如果鸡蛋碎了，那么对应的是 f(T - 1, K - 1)f(T−1,K−1)，也就是说在这一层的下方可以有 f(T - 1， K - 1)f(T−1，K−1) 层。
    public int superEggDrop(int K, int N) {
        if (N == 1)
            return 1;

        int[][] f = new int[N + 1][K + 1];//做T次操作，有K个鸡蛋时，能找到答案的最高的N层楼
        for (int i = 1; i <= K; ++i)
            f[1][i] = 1;

        int ans = -1;
        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= K; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];//状态转移方程
            }
            if (f[i][K] >= N) {
                ans = i;
                break;
            }
        }
        return ans;
    }


    //dp+二分搜索
    public int superEggDrop2(int K, int N) {
        return dp(K, N);
    }

    Map<Integer, Integer> memo = new HashMap();

    public int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K - 1, x - 1);
                    int t2 = dp(K, N - x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }
                ans = 1 + Math.min(Math.max(dp(K - 1, lo - 1), dp(K, N - lo)),
                        Math.max(dp(K - 1, hi - 1), dp(K, N - hi)));
            }
            memo.put(N * 100 + K, ans);
        }
        return memo.get(N * 100 + K);
    }

}
