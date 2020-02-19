package _courses.imooc.algorithmic_interview.HashTable.others._136;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: Using hashtable to find the one
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @date: 2019/3/3 19:04
 */
public class Solution {
    public int singleNumber(int[] nums) {
        assert (nums.length % 2 == 1);//声明传参合法

        Set<Integer> hashtable = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            if (!hashtable.contains(nums[i]))
                hashtable.add(nums[i]);
            else
                hashtable.remove(nums[i]);

        assert (hashtable.size() == 1);
        return hashtable.iterator().next();
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 2};
        System.out.println(new Solution().singleNumber(nums));
    }
}
