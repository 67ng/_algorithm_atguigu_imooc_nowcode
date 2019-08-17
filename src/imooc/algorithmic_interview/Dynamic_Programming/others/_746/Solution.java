package imooc.algorithmic_interview.Dynamic_Programming.others._746;

/**
 * Description:
 *
 * @date: 2019/2/2 23:03
 */
public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length + 1];
        memo[0] = cost[0];//没有台阶
        memo[1] = cost[1];//一级台阶
        for (int i = 2; i <= cost.length; i++) {
            int costValue = (i == cost.length) ? 0 : cost[i];
            memo[i] = Math.min(memo[i - 1], memo[i - 2]) + costValue;
        }
        return memo[cost.length];
    }

    public static void main(String[] args) {
//        int[] cost = {10, 15, 20};
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(new Solution().minCostClimbingStairs(cost));
    }
}
