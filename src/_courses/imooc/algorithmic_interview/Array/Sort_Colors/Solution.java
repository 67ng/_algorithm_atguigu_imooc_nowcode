package _courses.imooc.algorithmic_interview.Array.Sort_Colors;

/**
 * Description:
 * 75. Sort Colors
 * https://leetcode.com/problems/sort-colors/description/
 * 计数排序的思路
 * 对整个数组遍历了两遍
 * 时间复杂度: O(n)
 * 空间复杂度: O(k), k为元素的取值范围
 *
 * @date: 2019/1/21 19:02
 */
public class Solution {
    //solution1:计数排序的思路
    //  对整个数组遍历了两遍
    // *时间复杂度: O(n)
    //  空间复杂度: O(k), k为元素的取值范围

    /**
     * public void sortColors(int[] nums) {
     * int[] count = new int[3];// 存放0, 1, 2三个元素的频率
     * for (int i = 0; i < nums.length; i++) {
     * assert nums[i] >= 0 && nums[i] <= 2;
     * count[nums[i]]++;
     * }
     * int index = 0;
     * for (int j = 0; j < 3; j++) {
     * for (int i = 0; i < count[j]; i++) {
     * nums[index++] = j;
     * }
     * }
     * }
     */

    //solution2:三路快速排序的思想
    // 对整个数组只遍历了一遍
    // 时间复杂度: O(n)
    // 空间复杂度: O(1)
    public void sortColors(int[] nums) {
        int zero = -1;// [0...zero] == 0
        int two = nums.length;// [two...n-1] == 2
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, --two);
            } else { // nums[i] == 0
                assert nums[i] == 0;
                swap(nums, i++, ++zero);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void printArr(int[] nums) {
        for (int num : nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {2, 2, 2, 1, 1, 0};
        (new Solution()).sortColors(nums);
        printArr(nums);
    }
}
