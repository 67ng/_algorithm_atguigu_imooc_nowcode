package imooc.algorithmic_interview.HashTable.others._136;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: Using the sum of vector and the sum of set
 * mathematically get the result
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @date: 2019/3/3 19:08
 */
public class Solution2 {
    public int singleNumber(int[] nums) {
        assert (nums.length % 2 == 1);

        Set<Integer> sets = new HashSet<>();
        for (int num : nums)
            sets.add(num);
        int resArray = 0;
        int resSet = 0;

        for (int set : sets)
            resSet += set;
        for (int num : nums)
            resArray += num;

        return 2 * resSet - resArray;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(new Solution2().singleNumber(nums));
    }
}
