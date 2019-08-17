package imooc.algorithmic_interview.Array.Others._215;


/**
 * Description:
 *
 * @date: 2019/1/27 20:36
 */
public class Solution {
    public static int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k - 1);
    }

    private static int findKthLargest(int[] nums, int l, int r, int k) {

        if (l == r)
            return nums[l];

        int p = partition(nums, l, r);

        if (p == k)
            return nums[p];
        else if (k < p)
            return findKthLargest(nums, l, p - 1, k);
        else // k > p
            return findKthLargest(nums, p + 1, r, k);
    }

    private static int partition(int[] nums, int l, int r) {

        int p = (int) Math.random() * (r - l + 1) + l;
        swap(nums, l, p);

        int lt = l + 1; //[l+1...lt) > p ; [lt..i) < p
        for (int i = l + 1; i <= r; i++)
            if (nums[i] > nums[l])
                swap(nums, i, lt++);

        swap(nums, l, lt - 1);

        return lt - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    /*private void swap2(int i, int j) {错误的交换方法
        int t = i;
        i = j;
        j = t;
    }*/

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;

        System.out.println(new Solution().findKthLargest(nums, k));
    }
}
