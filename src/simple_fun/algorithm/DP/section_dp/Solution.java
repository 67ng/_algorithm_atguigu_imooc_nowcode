package simple_fun.algorithm.DP.section_dp;

/**
 * @Description: 区间动态规划的问题汇总
 * @Author: 67ng
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

    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int mod = 1000000007;
        int[][][] dp = new int[4][n][n];

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    char c = (char) ('a' + k);
                    if (j == i) {//base case1
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
                                dp[k][i][j] = 2; // "aa" : {"a", "aa"}base case2
                            else { // length is > 2
                                dp[k][i][j] = 2;
                                for (int m = 0; m < 4; ++m) { // count each one within subwindows [i+1][j-1]
                                    dp[k][i][j] += dp[m][i + 1][j - 1];
                                    dp[k][i][j] %= mod;
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
        return 0;
    }

}
