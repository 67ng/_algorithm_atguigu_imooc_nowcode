package _courses.imooc.algorithmic_interview.HashTable.others._136;

/**
 * Description: Using the attribution of xor operation:
 * a ^ 0 = a
 * a ^ a = 0
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @date: 2019/3/3 19:08
 */
public class Solution3 {
    public int singleNumber(int[] nums) {
        assert (nums.length % 2 == 1);

        int res = 0;
        for (int i = 0; i < nums.length; i++)
            res ^= nums[i];
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(new Solution3().singleNumber(nums));

    }

}
