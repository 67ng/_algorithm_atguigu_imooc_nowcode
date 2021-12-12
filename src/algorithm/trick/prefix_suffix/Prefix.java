package algorithm.trick.prefix_suffix;

/**
 * @Description: 前缀和
 * @Date: 2021/12/12
 */

public class Prefix {
    // 从左往右统计连续递减的元素数量
    public static int[] getMonotonousCnt(int[] nums) {
        int[] left = new int[nums.length];
        for (int i = 1; i < nums.length; i++)
            if (nums[i] <= nums[i - 1])
                left[i] = left[i - 1] + 1;
        return left;
    }
}
