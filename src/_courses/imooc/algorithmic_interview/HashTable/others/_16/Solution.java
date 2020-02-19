package _courses.imooc.algorithmic_interview.HashTable.others._16;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/2/3 22:22
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        assert (nums.length >= 3);

        Arrays.sort(nums);

        int diff = Math.abs(nums[0] + nums[1] + nums[2] - target);//初始差距
        int res = nums[0] + nums[1] + nums[2];//初始返回

        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            int t = target - nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == t) {//若相差为零直接返回
                    return nums[i] + nums[l] + nums[r];
                }else {
                    if (Math.abs(nums[l] + nums[r] - t) < diff) {
                        diff = Math.abs(nums[l] + nums[r] - t);
                        res = nums[i] + nums[l] + nums[r];
                    }
                    if (nums[l] + nums[r] < t)
                        l++;
                    else   // nums[l] + nums[r] > t
                        r--;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(new Solution().threeSumClosest(nums, target));
    }
}
