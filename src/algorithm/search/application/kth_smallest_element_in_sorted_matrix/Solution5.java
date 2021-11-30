package algorithm.search.application.kth_smallest_element_in_sorted_matrix;

/**
 * @Description: Zigzag搜索
 * @Date: 2021/11/30
 */

public class Solution5 {
    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        int row = 0; // we start from the upper-right corner
        int col = n - 1;

        for (int cnt_le = 0, cnt_lt = 0; true; cnt_le = 0, cnt_lt = 0) {
            for (int i = 0, j = n - 1, p = n - 1; i < n; i++) {
                while (j >= 0 && matrix[i][j] > matrix[row][col]) j--; // pointer j for counting cnt_le
                cnt_le += (j + 1);
                while (p >= 0 && matrix[i][p] >= matrix[row][col]) p--; // pointer p for counting cnt_lt
                cnt_lt += (p + 1);
            }

            if (cnt_le < k) { // candidate solution too small so increase it
                row++;
            } else if (cnt_lt >= k) { // candidate solution too large so decrease it
                col--;
            } else { // candidate solution equal to the kth smallest element so return
                return matrix[row][col];
            }
        }
    }
}
