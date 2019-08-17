package imooc.algorithmic_interview.Array.Others._88;

import java.util.Arrays;

/**
 * Description:
 *
 * @date: 2019/1/27 17:19
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        assert (nums1.length == m + n && nums2.length == n);

        for (int i = n + m - 1; i >= n; i--)
            nums1[i] = nums1[i - n];

        int i = n;  // pointer for nums1 [n, n+m)
        int j = 0;  // pointer for nums2 [0, n)
        int k = 0;  // pointer merged nums1 [0, n+m)
        while (k < n + m) {
            if (i >= n + m)//num1取完了
                nums1[k++] = nums2[j++];
            else if (j >= n)//num2取完了
                nums1[k++] = nums1[i++];
            else if (nums1[i] < nums2[j])
                nums1[k++] = nums1[i++];
            else//nums1[i] >= nums2[j]
                nums1[k++] = nums2[j++];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 4;
        int n = 3;
        new Solution().merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
