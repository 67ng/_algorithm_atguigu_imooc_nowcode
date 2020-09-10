package data_structure.linear.queue.monotone_queue;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @Description: 单调队列
 *
 * @Link: https://leetcode-cn.com/problems/sliding-window-maximum/solution/dan-diao-dui-lie-by-labuladong/
 * @Author: matreeix
 * @Date: 2020/9/10
 */

public class MonotoneQueueDemo {

    private class MonotonicQueue {

        ArrayDeque<Integer> deque;

        public MonotonicQueue() {
            deque = new ArrayDeque<Integer>();
        }

        public Integer max() {
            return deque.peekFirst();
        }

        public void pop(int num) {
            if (!deque.isEmpty() && num == deque.peekFirst())
                deque.pollFirst();
        }

        public void push(int num) {
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);
        }

    }

    /**
     * 239. 滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue windows = new MonotonicQueue();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                windows.push(nums[i]);
            } else {
                windows.push(nums[i]);
                res.add(windows.max());
                windows.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

}
