package algorithm.DP.linear_dp;

/**
 * @Name: 53.最大子序和
 * @Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * @Linked: https://leetcode-cn.com/problems/maximum-subarray/
 */

public class MaximumSubarray {

    //贪心算法，时间复杂度O(n)，空间复杂度O(1)
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);//包含当前元素的最大和
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    //DP（Kadane算法），时间复杂度O(n)，空间复杂度O(1)
    public int maxSubArray2(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0)
                nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
