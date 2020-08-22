package algorithm.DP.digit_dp;

/**
 * @Description: 902. 最大为 N 的数字组合
 * 我们有一组排序的数字 D，它是  {'1','2','3','4','5','6','7','8','9'} 的非空子集。（请注意，'0' 不包括在内。）
 * 现在，我们用这些数字进行组合写数字，想用多少次就用多少次。例如 D = {'1','3','5'}，我们可以写出像 '13', '551', '1351315' 这样的数字。
 * 返回可以用 D 中的数字写出的小于或等于 N 的正整数的数目。
 * <p>
 * 链接： https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set/
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class NumbersAtMostNGivenDigitSet {
    public int atMostNGivenDigitSet(String[] D, int N) {
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K + 1];//dp[i] 表示小于等于 N 中最后 |N| - i 位数的合法数的个数
        dp[K] = 1;

        for (int i = K - 1; i >= 0; --i) {
            // compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d : D) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(D.length, K - i - 1);
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i + 1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i);
        return dp[0];
    }
}
