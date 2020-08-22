package algorithm.DP.linear_dp.stock_trading;

/**
 * @Name: 309.最佳买卖股票时机含冷冻期
 * @Description: 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 1.你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 2.卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class StockTradingWithCooldown {

    public int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}
