package algorithm.DP.game_dp;

/**
 * @Description: 1140. 石子游戏 II
 * 亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * 亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。
 * 链接： https://leetcode-cn.com/problems/stone-game-ii/
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class StoneGameII {
    private int[] sums;//the sum from piles[i] to the end
    private int[][] hash;

    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) return 0;
        int n = piles.length;
        sums = new int[n];
        sums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sums[i] = sums[i + 1] + piles[i]; //the sum from piles[i] to the end
        }

        hash = new int[n][n];
        return helper(piles, 0, 1);
    }

    private int helper(int[] a, int i, int M) {
        if (i == a.length) return 0;
        if (2 * M >= a.length - i) {
            return sums[i];
        }
        if (hash[i][M] != 0) return hash[i][M];
        int min = Integer.MAX_VALUE;//the min value the next player can get
        for (int x = 1; x <= 2 * M; x++) {
            min = Math.min(min, helper(a, i + x, Math.max(M, x)));
        }
        hash[i][M] = sums[i] - min;  //max stones = all the left stones - the min stones next player can get
        return hash[i][M];
    }


    /**
     * dp[i][j]表示剩余[i : len - 1]堆时，M = j的情况下，先取的人能获得的最多石子数
     *
     * i + 2M >= len, dp[i][M] = sum[i : len - 1], 剩下的堆数能够直接全部取走，那么最优的情况就是剩下的石子总和
     * i + 2M < len, dp[i][M] = max(dp[i][M], sum[i : len - 1] - dp[i + x][max(M, x)]), 其中 1 <= x <= 2M，剩下的堆数不能全部取走，那么最优情况就是让下一个人取的更少。对于我所有的x取值，下一个人从x开始取起，M变为max(M, x)，所以下一个人能取dp[i + x][max(M, x)]，我最多能取sum[i : len - 1] - dp[i + x][max(M, x)]。
     *
     * 作者：lgh18
     * 链接：https://leetcode-cn.com/problems/stone-game-ii/solution/java-dong-tai-gui-hua-qing-xi-yi-dong-17xing-by-lg/
     * */
    public int stoneGameII2(int[] piles) {
        int len = piles.length, sum = 0;
        int[][] dp = new int[len][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];
            for (int M = 1; M <= len; M++) {
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(M, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

}
