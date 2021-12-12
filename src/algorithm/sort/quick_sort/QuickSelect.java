package algorithm.sort.quick_sort;

import java.util.Random;

/**
 * @Description: 快速选择算法
 * 和快排的区别：快排需要递归划分的两边，快选只需要递归一边，平均时间复杂度为O(n)，迭代写法空间复杂度O(1)
 * 模板题：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @Date: 2021/12/12
 */

public class QuickSelect {
    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     */
    public static int findKthLargest(int[] nums, int k) {
        return quick_select(nums, 0, nums.length - 1, k);
    }

    public static int quick_select(int[] nums, int l, int r, int k) {
        Random rand = new Random();
        while (true) {
            if (l == r) return nums[l];
//            int i = l - 1, j = r + 1, x = nums[l + r >> 1]; // 选取 nums[l], 极端样例 时间会很久
            int x = nums[rand.nextInt(Integer.MAX_VALUE) % (r - l + 1) + l], i = l - 1, j = r + 1; // 随机选取
            while (i < j) {
                do i++; while (nums[i] > x);
                do j--; while (nums[j] < x);
                if (i < j) swap(nums, i, j);
            }

            // 将 递归 的 参数l,r,k变化 改为 while 循环中 l,r,k 更新, 省去递归栈空间
            // if (k <= j - l + 1) return quick_select(nums, l, j, k);
            if (k <= j - l + 1) {
                r = j;
            }
            // else return quick_select(nums, j + 1, r, k - (j - l + 1));
            else {// 注意 k更新用到 l, 所以 l 更新应该在 k更新之后
                k = k - (j - l + 1);
                l = j + 1;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
