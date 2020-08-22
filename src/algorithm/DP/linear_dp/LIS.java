package algorithm.DP.linear_dp;

/**
 * @Name: 300.最长上升子序列
 * @Description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * @Linked: https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
 */

public class LIS {
    //DP，时间复杂度O(n^2)，空间复杂度O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];//dp[j] 代表nums[0...j]中以nums[j]结尾的最长上升子序列
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;//状态转移方程dp[i]=max(dp[j])+1
            maxans = Math.max(maxans, dp[i]);//LIS = max(dp[i]),其中0≤i<n
        }
        return maxans;
    }

    //贪心和二分查找，时间复杂度O(nlogn)，空间复杂度O(n)
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) return 0;
        int[] d = new int[n + 1];//d[i] 表示长度为 i 的最长上升子序列的末尾元素的最小值
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];//如果nums[i] > d[len]，则直接加入到d数组末尾，并更新len=len+1
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[l]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else r = mid - 1;
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
