package _courses.imooc.algorithmic_interview.Array.Two_Sum_II;

/**
 * Description:167. Two Sum II - Input array is sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *
 * @date: 2019/1/21 19:36
 */
public class Solution {
    //solution1:暴力枚举法
    //时间复杂度: O(n^2)
    // 空间复杂度: O(1)
  /**  public int[] twoSum(int[] numbers, int target) {
        if(numbers.length < 2 || !isSorted(numbers))
            throw new IllegalArgumentException("Illegal argument numbers");

        for(int i = 0 ; i < numbers.length ; i ++)
            for(int j = i+1 ; j < numbers.length ; j ++)
                if(numbers[i] + numbers[j] == target){
                    int[] res = {i+1, j+1};
                    return res;
                }

        throw new IllegalStateException("The input has no solution");
    }*/

    //solution2:二分搜索法
    // 时间复杂度: O(nlogn)
    // 空间复杂度: O(1)
  /**public int[] twoSum(int[] numbers, int target) {
      if(numbers.length < 2 || !isSorted(numbers))
          throw new IllegalArgumentException("Illegal argument numbers");

      for(int i = 0 ; i < numbers.length - 1 ; i ++){
          int j = binarySearch(numbers, i+1, numbers.length-1, target - numbers[i]);
          if(j != -1){
              int[] res = {i+1, j+1};
              return res;
          }
      }

      throw new IllegalStateException("The input has no solution");
  }*/

  //solution3: 对撞指针
  // 时间复杂度: O(n)
  // 空间复杂度: O(1)
  public int[] twoSum(int[] numbers, int target) {
      if(numbers.length < 2 || !isSorted(numbers))
          throw new IllegalArgumentException("Illegal argument numbers");

      int l = 0, r = numbers.length - 1;
      while(l < r){
          if(numbers[l] + numbers[r] == target){
              int[] res = {l+1, r+1};
              return res;
          }
          else if(numbers[l] + numbers[r] < target)
              l ++;
          else // numbers[l] + numbers[r] > target
              r --;
      }
      throw new IllegalStateException("The input has no solution");
  }

    private int binarySearch(int[] nums, int l, int r, int target){

        if(l < 0 || l > nums.length)
            throw new IllegalArgumentException("l is out of bound");

        if(r < 0 || r > nums.length)
            throw new IllegalArgumentException("r is out of bound");

        while(l <= r){
            int mid = l + (r - l)/2;
            if(nums[mid] == target)
                return mid;
            if(target > nums[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }

        return -1;
    }


    private boolean isSorted(int[] numbers){
        for(int i = 1 ; i < numbers.length ; i ++)
            if(numbers[i] < numbers[i-1])
                return false;
        return true;
    }

    private static void printArr(int[] nums){
        for(int num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;
        printArr((new Solution()).twoSum(nums, target));
    }
}
