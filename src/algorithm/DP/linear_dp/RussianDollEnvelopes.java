package algorithm.DP.linear_dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Name: 354.俄罗斯套娃信封问题
 * @Description: 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。不允许旋转信封。
 * @Linked: https://leetcode-cn.com/problems/russian-doll-envelopes/
 */

public class RussianDollEnvelopes {
    //排序+LIS
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
}
