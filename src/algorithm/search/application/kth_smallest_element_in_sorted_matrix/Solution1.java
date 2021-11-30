package algorithm.search.application.kth_smallest_element_in_sorted_matrix;

import java.util.Arrays;

/**
 * @Description: 暴力解法
 * @Date: 2021/11/30
 */

public class Solution1 {
    /**
     * 时间复杂度：O(n^2 * log(n^2))
     * 空间复杂度：O(n^2)
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, i = 0;
        int[] nums = new int[n * n];
        for (int[] row : matrix)
            for (int ele : row)
                nums[i++] = ele;
        Arrays.sort(nums);
        return nums[k - 1];
    }

}
