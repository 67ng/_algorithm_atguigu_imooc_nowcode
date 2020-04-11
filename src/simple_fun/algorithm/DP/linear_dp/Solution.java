package simple_fun.algorithm.DP.linear_dp;

/**
 * @Description: 线性动态规划的问题汇总
 * @Author: 67ng
 * @Date: 2020/4/11
 */
public class Solution {

    /**
     * @Name: 300.最长上升子序列
     * @Description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * @Linked: https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     */
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

    /**
     * @Name: 1143.最长公共子序列
     * @Description: 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
     * @Linked: https://leetcode-cn.com/problems/longest-common-subsequence/solution/a-fei-xue-suan-fa-zhi-by-a-fei-8/
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];//dp[i][j]表示索引分别为i、j的s1和s2的LCS长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }

    /**
     * @Name:
     * @Description:
     * @Linked:
     */


    public static void main(String[] args) {


    }

}
