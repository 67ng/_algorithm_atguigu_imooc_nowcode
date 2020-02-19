package _courses.imooc.algorithmic_interview.Array.Others._35;

/**
 * Description:
 *
 * @date: 2019/3/16 21:12
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == target) {
                break;
            } else if (nums[i] < target) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println(new Solution().searchInsert(arr, 5));
        System.out.println(new Solution().searchInsert(arr, 2));
        System.out.println(new Solution().searchInsert(arr, 7));
        System.out.println(new Solution().searchInsert(arr, 0));

    }
}