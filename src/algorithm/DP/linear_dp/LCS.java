package algorithm.DP.linear_dp;

/**
 * @Name: 1143.最长公共子序列
 * @Description: 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * @Linked: https://leetcode-cn.com/problems/longest-common-subsequence/solution/a-fei-xue-suan-fa-zhi-by-a-fei-8/
 */

public class LCS {
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

}
