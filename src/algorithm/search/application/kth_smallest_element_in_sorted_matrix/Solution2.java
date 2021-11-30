package algorithm.search.application.kth_smallest_element_in_sorted_matrix;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @Description: 优先队列
 * @Date: 2021/11/30
 */

public class Solution2 {
    /**
     * 时间复杂度：O(n^2 * logk)
     * 空间复杂度：O(k)
     * */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] row : matrix) {
            for (int ele : row) {
                pq.offer(ele);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
}
