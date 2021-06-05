package data_structure.linear.queue.monotone_queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * @Description: 单调队列
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

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        //创建双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        //先初始化前K个元素
        for (int i = 0; i < k; i++) {
            //判断队列是否为空 或者当前入队元素是否大于队尾元素 大于则出队
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //当前元素入队
            //由于需要判断当前元素是否在窗口中，所以实际上队列中存储的为当前元素的下标
            //根据下标找元素比根据元素找下标方便
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        //添加当前最大元素
        ans[0] = nums[deque.peekFirst()];

        for (int i = k; i < n; i++) {
            //判断队列是否为空 或者当前入队元素是否大于队尾元素 大于则出队
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //当前元素入队
            deque.offerLast(i);
            //判断队首元素是否在窗口中
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            //添加答案
            ans[i - k + 1] = nums[deque.peekFirst()];
        }

        return ans;
    }


}
