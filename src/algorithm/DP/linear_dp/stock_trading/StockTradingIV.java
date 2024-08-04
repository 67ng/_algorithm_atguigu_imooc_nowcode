package algorithm.DP.linear_dp.stock_trading;

/**
 * @Name: 188.买卖股票的最佳时机 IV
 * @Description: 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */

public class StockTradingIV {

    public int maxProfit4(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2)
            return StockTradingII.maxProfit2(prices);//对应max_k取无穷大的情况

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */ }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }
}
