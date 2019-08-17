package imooc.algorithmic_interview.Array.Others._27;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/1/27 15:12
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        int count = 0;//统计不含val值的元素总数
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        } else {
            for (int i = 0; i <= nums.length - 1; i++) {
                if (nums[i] != val) {
                    nums[count++] = nums[i];
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 0, 1, 3, 3, 3, 2, 2, 3, 0, 4, 2, 2, 3};
        int[] nums2 = {3, 3, 3};
        int[] nums3 = {};
        int val = 3;
        System.out.println(new Solution().removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
}
