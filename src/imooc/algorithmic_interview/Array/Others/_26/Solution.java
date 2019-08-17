package imooc.algorithmic_interview.Array.Others._26;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/1/27 14:14
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = i + 1;
        int s = 0;//统计总数
        for (; i < nums.length - 1; i++, j++) {
            if (nums[j] == nums[i] && j <= nums.length - 1) {
                continue;
            } else if (nums[j] != nums[i] && j <= nums.length - 1) {
                nums[++s] = nums[j];//直接赋值不要交换
            }
        }
        return s + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(new Solution().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
