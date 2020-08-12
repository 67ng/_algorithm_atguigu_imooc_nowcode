package simple_fun.algorithm.DP.linear_dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: 线性动态规划的问题汇总
 * @Author: matreeix
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


    //  (ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง字符串的DP问题(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง

    /**
     * @Name: 72.编辑距离
     * @Description: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 1.插入一个字符
     * 2.删除一个字符
     * 3.替换一个字符
     * @Linked: https://leetcode-cn.com/problems/edit-distance/
     */

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0)
            return n + m;
        // DP 数组，用 D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离。
        int[][] D = new int[n + 1][m + 1];
        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) //字符串 A 为空，如从空转换到 ro，显然编辑距离为字符串 B 的长度，这里是 2；
            D[i][0] = i;
        for (int j = 0; j < m + 1; j++) //同上理
            D[0][j] = j;

//        状态转移方程：
//        若 A 和 B 的最后一个字母相同：
//        D[i][j] =min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1])
//        若 A 和 B 的最后一个字母不同：
//        D[i][j]=1+min(D[i][j−1],D[i−1][j],D[i−1][j−1])

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * @Name: 44.通配符匹配
     * @Description: 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * @Linked: https://leetcode-cn.com/problems/wildcard-matching/
     */
    //DP，时间复杂度O(S*P)，空间复杂度O(S*P)
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        // base cases
        if (p.equals(s) || p.equals("*")) return true;
        if (p.isEmpty() || s.isEmpty()) return false;

        // init all matrix except [0][0] element as False
        boolean[][] d = new boolean[pLen + 1][sLen + 1];
        d[0][0] = true;

        // DP compute
        for (int pIdx = 1; pIdx < pLen + 1; pIdx++) {
            // the current character in the pattern is '*'
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;
                // d[p_idx - 1][s_idx - 1] is a string-pattern match
                // on the previous step, i.e. one character before.
                // Find the first idx in string with the previous math.
                while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) sIdx++;
                // If (string) matches (pattern),
                // when (string) matches (pattern)* as well
                d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];
                // If (string) matches (pattern),
                // when (string)(whatever_characters) matches (pattern)* as well
                while (sIdx < sLen + 1) d[pIdx][sIdx++] = true;
            }
            // the current character in the pattern is '?'
            else if (p.charAt(pIdx - 1) == '?') {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++)
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
            }
            // the current character in the pattern is not '*' or '?'
            else {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                    // Match is possible if there is a previous match
                    // and current characters are the same
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] &&
                            (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                }
            }
        }
        return d[pLen][sLen];
    }

    //回溯，时间复杂度O(SlogP)，空间复杂度O(1)
    public boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }


    //  (ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́使用状态机解决股票买卖系列问题)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง(ง•̀_•́)ง
    /**
     for 状态1 in 状态1的所有取值：
     for 状态2 in 状态2的所有取值：
     for ...
     dp[状态1][状态2][...] = 择优(选择1，选择2...)

     此问题下即是:
     dp[i][k][0 or 1]
     0 <= i <= n-1, 1 <= k <= K
     n 为天数，大 K 为最多交易数
     此问题共 n × K × 2 种状态，全部穷举就能搞定。
     for 0 <= i < n:
     for 1 <= k <= K:
     for s in {0, 1}:
     dp[i][k][s] = max(buy, sell, rest)
     求dp[n - 1][K][0]。

     状态转移方程：
     1.持有股票 dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     2.没有股票 dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

     base case：
     dp[-1][k][0] = dp[i][0][0] = 0
     dp[-1][k][1] = dp[i][0][1] = -infinity
     **/

    /**
     * @Name: 121.买卖股票的最佳时机
     * @Description:给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;//空间复杂度降为O(1)
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    /**
     * @Name: 122.买卖股票的最佳时机 II
     * @Description: 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     */

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * @Name: 123.买卖股票的最佳时机 III
     * @Description: 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     */

    public int maxProfit3(int[] prices) {
        int max_k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    /*处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];

    }

    //这里 k 取值范围比较小，所以可以不用 for 循环，直接把 k = 1 和 2 的情况手动列举出来也可以：
    public int maxProfit3_2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }


    /**
     * @Name: 188.买卖股票的最佳时机 IV
     * @Description: 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
     */

    public int maxProfit4(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2)
            return maxProfit2(prices);//对应max_k取无穷大的情况

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */ }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }


    /**
     * @Name: 309.最佳买卖股票时机含冷冻期
     * @Description: 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 1.你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 2.卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     */

    public int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    /**
     * @Name: 714.买卖股票的最佳时机含手续费
     * @Description: 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * @Linked: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     */

    public int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);//在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
        }
        return dp_i_0;
    }

    /**
     * @Name: 53.最大子序和
     * @Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @Linked: https://leetcode-cn.com/problems/maximum-subarray/
     */

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

    /**
     * @Name: 152.乘积最大子数组
     * @Description: 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
     * @Linked: https://leetcode-cn.com/problems/maximum-product-subarray/
     */

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * @Name: 887.鸡蛋掉落
     * @Description: 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     * 你的目标是确切地知道 F 的值是多少。
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     * @Linked: https://leetcode-cn.com/problems/super-egg-drop/
     */

    //经典的谷歌面试题，现仅介绍一种独特的数学法
//    如果鸡蛋没有碎，那么对应的是 f(T - 1, K)f(T−1,K)，也就是说在这一层的上方可以有 f(T - 1, K)f(T−1,K) 层；
//    如果鸡蛋碎了，那么对应的是 f(T - 1, K - 1)f(T−1,K−1)，也就是说在这一层的下方可以有 f(T - 1， K - 1)f(T−1，K−1) 层。
    public int superEggDrop(int K, int N) {
        if (N == 1)
            return 1;

        int[][] f = new int[N + 1][K + 1];//做T次操作，有K个鸡蛋时，能找到答案的最高的N层楼
        for (int i = 1; i <= K; ++i)
            f[1][i] = 1;

        int ans = -1;
        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= K; ++j) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];//状态转移方程
            }
            if (f[i][K] >= N) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    /**
     * @Name: 354.俄罗斯套娃信封问题
     * @Description: 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。不允许旋转信封。
     * @Linked:
     */

    public int LIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // 1.sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1];//若相等则按照第二个维度降序，主要是保证不算到LIS里
                } else {
                    return arr1[0] - arr2[0];//按照第一个维度升序
                }
            }
        });
        // 2.extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i)
            secondDim[i] = envelopes[i][1];
        return LIS(secondDim);//求第二个维度的LIS
    }


    public static void main(String[] args) {


    }

}
