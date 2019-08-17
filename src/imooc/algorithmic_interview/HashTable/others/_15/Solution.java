package imooc.algorithmic_interview.HashTable.others._15;

import java.util.*;

/**
 * Description:Using two pointer technique
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * @date: 2019/2/3 21:01
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);//必须先排序

        List<List<Integer>> res = new ArrayList<>();
        int index = 0;
        while (index < nums.length) {//移动index
            if (nums[index] > 0)
                break;

            int bindex = index + 1;//前指针
            int cindex = nums.length - 1;//后指针
            while (bindex < cindex) {//固定index

                if (nums[bindex] + nums[cindex] == -nums[index]) {
                    res.add(Arrays.asList(nums[index], nums[bindex], nums[cindex]));


                    bindex = next_num_index(nums, bindex);
                    cindex = pre_num_index(nums, cindex);
                } else if (nums[bindex] + nums[cindex] < -nums[index]) {
                    bindex = next_num_index(nums, bindex);
                } else {//nums[bindex] + nums[cindex] > -nums[index]
                    cindex = pre_num_index(nums, cindex);
                }
            }
            index = next_num_index(nums, index);
        }

        return res;
    }

    private int next_num_index(int[] nums, int cur) {//求cur索引下一个不同元素的索引

        for (int i = cur + 1; i < nums.length; i++)
            if (nums[i] != nums[cur])
                return i;
        return nums.length;
    }

    private int pre_num_index(int[] nums, int cur) {//求cur索引前一个不同元素的索引

        for (int i = cur - 1; i >= 0; i--)
            if (nums[i] != nums[cur])
                return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(new Solution().threeSum(nums).toString());
    }
}
