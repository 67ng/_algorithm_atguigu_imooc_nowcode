package imooc.algorithmic_interview.Dynamic_Programming.others._122;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * Description:使用贪心算法
 *
 * @date: 2019/3/17 10:44
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int minnumber = prices[0];
        int sum = 0;//阶段上升时的最大利润
        int res = 0;//总的最大利润
        for (int i = 1; i < len; i++) {
            if (prices[i - 1] > prices[i]) {
                res += sum;
                sum = 0;
                minnumber = prices[i];
            } else {
                sum = prices[i] - minnumber;
            }
        }
        res += sum;
        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {7, 6, 4, 3, 1};
        int[] arr4 = {6, 1, 3, 2, 4, 7};
        System.out.println(new Solution().maxProfit(arr1));
        System.out.println(new Solution().maxProfit(arr2));
        System.out.println(new Solution().maxProfit(arr3));
        System.out.println(new Solution().maxProfit(arr4));//7
    }
}
