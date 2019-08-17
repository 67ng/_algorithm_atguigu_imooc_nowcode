package imooc.algorithmic_interview.Array.Others._80;

import java.util.Arrays;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0;//统计总数
        int i = 0;
        while (i < nums.length) {
            int k = nextIndex(nums, i);
            int len = Math.min(2, k - i);
            for (int j = 0; j < len; j++) {
                nums[count + j] = nums[i];
            }
            count += len;
            i = k;
        }
        return count;
    }

    private int nextIndex(int[] nums, int index) {//返回从index开始下一个不同元素值的索引
        for (int i = index; i < nums.length; i++)
            if (nums[i] != nums[index])
                return i;
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(new Solution().removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}