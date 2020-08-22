package algorithm.DP.game_dp;

import java.util.Arrays;

/**
 * @Description: 1406. 石子游戏 III
 * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
 * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
 * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
 * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
 * 链接：https://leetcode-cn.com/problems/stone-game-iii/
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class StoneGameIII {

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] f = new int[n + 1];//f[i] = max(sum(i,j−1)−f[j]),j∈[i+1,i+3]​

        Arrays.fill(f, Integer.MIN_VALUE);
        // 边界情况，当没有石子时，分数为 0
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            int pre = 0;
            for (int j = i + 1; j <= i + 3 && j <= n; ++j) {
                pre += stoneValue[j - 1];
                f[i] = Math.max(f[i], pre - f[j]);
            }
        }
        if (f[0] == 0) {
            return "Tie";
        } else {
            return f[0] > 0 ? "Alice" : "Bob";
        }
    }

}
