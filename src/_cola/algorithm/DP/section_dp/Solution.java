package _cola.algorithm.DP.section_dp;

/**
 * @Description: 区间动态规划的问题汇总
 * @Author: matreeix
 * @Date: 2020/4/11
 */
public class Solution {

    /**
     * @Name: 516.最长回文子序列
     * @Description: 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
     * @Linked: https://leetcode-cn.com/problems/longest-palindromic-subsequence/
     */

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];//f[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少。
        for (int i = n - 1; i >= 0; i--) {//注意，遍历顺序要确保所有子问题都已经求解过
            f[i][i] = 1;//base case
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;//状态转移方程1
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);//状态转移方程2
                }
            }
        }
        return f[0][n - 1];
    }

    /**
     * @Name: 730.统计不同回文子字符串
     * @Description: 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
     * 通过从 S 中删除 0 个或多个字符来获得子字符序列。
     * 如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。
     * 如果对于某个  i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。
     * @Linked: https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
     */

    /**
     * 算法：
     * 定义 dp[x][i][j] 为子串 S[i...j] 拥有不同回文子字符串的答案，且 S[i] == S[j] == 'a'+x。由于字符串只包含四个字符 a, b, c, d，因此 0 <= x < 4。dp 的公式如下：
     * <p>
     * 如果 S[i] != 'a'+x，则 dp[x][i][j] = dp[x][i+1][j]
     * 如果 S[j] != 'a'+x，则 dp[x][i][j] = dp[x][i][j-1]
     * 如果 S[i] == S[j] == 'a'+x，则 dp[x][i][j] = 2 + dp[0][i+1][j-1] + dp[1][i+1][j-1] + dp[2][i+1][j-1] + dp[3][i+1][j-1]。
     * <p>
     * 当第一个和最后一个字符相同时，我们需要计算子串 S[i+1][j-1] 中所有不同的回文（a、b、c、d 中的每一个）加上第一个和最后一个字符的两个回文。
     * 设 n 为字符串 S 的长度，则最终的答案为 dp[0][0][n-1] + dp[1][0][n-1] + dp[2][0][n-1] + dp[3][0][n-1] mod 1000000007
     **/


    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int mod = 1000000007;
        int[][][] dp = new int[4][n][n];//dp[x][i][j] 为子串 S[i...j] 拥有不同回文子字符串的子问题答案。
        // 例如，dp[0][i][j]为子串S[i...j]且含有a的的子结果
        // 例如，dp[1][i][j]为子串S[i...j]且含有b的的子结果
        // 例如，dp[2][i][j]为子串S[i...j]且含有c的的子结果
        // 例如，dp[3][i][j]为子串S[i...j]且含有d的的子结果

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    char c = (char) ('a' + k);
                    if (j == i) {//base case
                        if (S.charAt(i) == c)
                            dp[k][i][j] = 1;
                        else
                            dp[k][i][j] = 0;
                    } else { // j > i
                        if (S.charAt(i) != c)
                            dp[k][i][j] = dp[k][i + 1][j];
                        else if (S.charAt(j) != c)
                            dp[k][i][j] = dp[k][i][j - 1];
                        else { // S[i] == S[j] == c
                            if (j == i + 1)
                                dp[k][i][j] = 2; // "aa" : {"a", "aa"}边界条件特殊处理
                            else { // length is > 2
                                dp[k][i][j] = 2;
                                for (int m = 0; m < 4; ++m) { // count each one within subwindows [i+1][j-1]
                                    dp[k][i][j] += dp[m][i + 1][j - 1];
                                    dp[k][i][j] %= mod;//注意子问题也不要越界
                                }
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int k = 0; k < 4; ++k) {
            ans += dp[k][0][n - 1];
            ans %= mod;
        }
        return ans;
    }

    /**
     * @Name: 1039.多边形三角剖分的最低得分
     * @Description: 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。
     * 假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。
     * 返回多边形进行三角剖分后可以得到的最低分。
     * @Linked: https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon/
     */

    public int minScoreTriangulation(int[] A) {
        int[][] dp = new int[50][50];
        for (int i = A.length - 1; i >= 0; --i)
            for (int j = i + 1; j < A.length; ++j)
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j] == 0 ?
                            Integer.MAX_VALUE : dp[i][j], dp[i][k] + A[i] * A[k] * A[j] + dp[k][j]);
        return dp[0][A.length - 1];
    }

    /**
     * @Name: 312.戳气球
     * @Description: 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。
     *  这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
     * 求所能获得硬币的最大数量。
     * @Linked: https://leetcode-cn.com/problems/burst-balloons/
     */

    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return maxCoins(nums, 0, nums.length - 1, dp);
    }

    public int maxCoins(int[] nums, int start, int end, int[][] dp) {
        if (start > end)
            return 0;
        if (dp[start][end] != 0)//利用了记忆化，剪枝
            return dp[start][end];

        int max = nums[start];
        //核心是自底向上的记忆化
        for (int i = start; i <= end; i++) {
            int val = maxCoins(nums, start, i - 1, dp) +
                    get(nums, i) * get(nums, start - 1) * get(nums, end + 1) +
                    maxCoins(nums, i + 1, end, dp);//在区间[start,end]中最后扎i气球

            max = Math.max(max, val);
        }
        dp[start][end] = max;//记录区间[start,end]的最大扎值
        return max;
    }

    public int get(int[] nums, int i) {
        if (i == -1 || i == nums.length)
            return 1;
        return nums[i];
    }

}
