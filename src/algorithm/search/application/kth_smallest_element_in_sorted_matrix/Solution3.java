package algorithm.search.application.kth_smallest_element_in_sorted_matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 优先队列优化
 * @Date: 2021/11/30
 */

public class Solution3 {
    /**
     * 时间复杂度：O(max(n, k) * logn)
     * 空间复杂度：O(n)
     * */
    public int kthSmallest(int[][] matrix, int k) {
        // (a[0], a[1]) and (b[0], b[1]) are positions in the matrix
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> matrix[a[0]][a[1]]));
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {i, 0}); // initialize the pool with elements from the first column
        }
        while (--k > 0) { // remove the smallest elements from the matrix (k-1) times
            int[] p = pq.poll();
            if (++p[1] < n) {
                pq.offer(p); // add the next element in the same row if it exists
            }
        }
        return matrix[pq.peek()[0]][pq.peek()[1]];
    }
}
