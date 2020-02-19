package _courses.imooc.algorithmic_interview.Dynamic_Programming.others._121;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/3/17 10:15
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[] mem = new int[len];//记录以i索引结尾的数组元素的最大差值，即利润
        int minnumber = prices[0];//记录以i索引结尾的数组元素中的最小值
        Arrays.fill(mem, -1);
        mem[0] = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] < minnumber) {
                minnumber = prices[i];
            }
            mem[i] = Math.max(mem[i - 1], prices[i] - minnumber);
        }
        if (mem[len - 1] > 0) {
            return mem[len - 1];
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {7, 6, 4, 3, 1};
        System.out.println(new Solution().maxProfit(arr1));
        System.out.println(new Solution().maxProfit(arr2));

    }
}
