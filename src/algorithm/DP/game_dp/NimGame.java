package algorithm.DP.game_dp;

/**
 * @Description: 292. Nim 游戏
 * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。你作为先手。
 * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 * 链接：https://leetcode-cn.com/problems/nim-game
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class NimGame {
    //推理法
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }

    //dp
    public boolean canWinNim2(int n) {
        // 使用包装类型的布尔数组，可以用 null 这个状态，表示当前 n 的结果还没有被计算出来
        Boolean[] memo = new Boolean[n + 1];
        return dfs(n, memo);
    }

    private boolean dfs(int n, Boolean[] memo) {
        if (n <= 3) {
            return true;
        }

        if (memo[n] != null) {
            return memo[n];
        }

        // 如果 3 种选择，只要有一种对方输掉，自己就可以赢
        if (!dfs(n - 1, memo) || !dfs(n - 2, memo) || !dfs(n - 3, memo)) {
            memo[n] = true;
            return true;
        }
        // 否则自己输
        memo[n] = false;
        return false;
    }

}
