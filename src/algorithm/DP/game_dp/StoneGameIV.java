package algorithm.DP.game_dp;

/**
 * @Description: 1510. 石子游戏 IV
 * Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
 * 一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
 * 如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
 * 给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
 * 链接：https://leetcode-cn.com/problems/stone-game-iv/
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class StoneGameIV {
    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k * k <= i; ++k) {
                if (!f[i - k * k]) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n];
    }
}
