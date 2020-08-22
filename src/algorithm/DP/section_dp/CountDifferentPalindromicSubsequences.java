package algorithm.DP.section_dp;

/**
 * @Name: 730.统计不同回文子字符串
 * @Description: 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
 * 通过从 S 中删除 0 个或多个字符来获得子字符序列。
 * 如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。
 * 如果对于某个  i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。
 * @Linked: https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
 */


public class CountDifferentPalindromicSubsequences {
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


}
