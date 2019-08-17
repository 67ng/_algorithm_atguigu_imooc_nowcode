package imooc.algorithmic_interview.Dynamic_Programming.others._714;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/3/17 16:52
 */
public class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1)
            return 0;

        int[] hold = {-prices[0], 0};//成本
        int[] cash = {0, 0};//利润
        for (int i = 1; i < prices.length; i++) {
            hold[i % 2] = Math.max(hold[(i - 1) % 2], cash[(i - 1) % 2] - prices[i]);
            cash[i % 2] = Math.max(cash[(i - 1) % 2], hold[(i - 1) % 2] + prices[i] - fee);
        }
        return cash[(prices.length - 1) % 2];

    }

    public static void main(String[] args) {
        int[] price = {1, 3, 2, 8, 4, 9};
        System.out.println(new Solution().maxProfit(price, 2));
    }
}
