package algorithm.search.application.kth_smallest_element_in_sorted_matrix;

/**
 * @Description: 浮点二分搜索
 * @Date: 2021/11/30
 */

public class Solution4 {
    /**
     * 时间复杂度：O(n * log(MAX - MIN))
     * 空间复杂度：O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0]; // minimum element in the matrix
        int r = matrix[n - 1][n - 1]; // maximum element in the matrix
        for (int cnt = 0; l < r; cnt = 0) { // this is the binary search loop
            int m = l + (r - l) / 2;
            for (int i = 0, j = n - 1; i < n; i++) {
                while (j >= 0 && matrix[i][j] > m)
                    j--; // the pointer j will only go in one direction
                cnt += (j + 1);
            }
            if (cnt < k) {
                l = m + 1; // cnt less than k, so throw away left half
            } else {
                r = m; // otherwise discard right half
            }
        }
        return l;
    }
}
