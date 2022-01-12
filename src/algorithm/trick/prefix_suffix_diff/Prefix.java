package algorithm.trick.prefix_suffix_diff;

/**
 * @Description: 前缀和
 * S[i] = a[1] + a[2] + ... a[i]
 * a[l] + ... + a[r] = S[r] - S[l - 1]
 * @Date: 2021/12/12
 */

public class Prefix {
    private int[] B;

    public Prefix(int[] nums) {
        B = new int[nums.length];
        B[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            B[i] = B[i - 1] + nums[i];
        }
    }

    // 从左往右统计连续递减的元素数量
    public static int[] getMonotonousCnt(int[] nums) {
        int[] left = new int[nums.length];
        for (int i = 1; i < nums.length; i++)
            if (nums[i] <= nums[i - 1])
                left[i] = left[i - 1] + 1;
        return left;
    }
}
