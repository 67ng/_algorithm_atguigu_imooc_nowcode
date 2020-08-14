package data_structure.linear.array.slide_window;

/**
 * Description:由于全是正数，可以用窗口不用map
 *
 * 给定一个数组arr，全是正数；一个整数aim，求累加和等
 * 于aim的，最长子数组，要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @author: matreeix
 * @date: 2018/11/2 9:12
 */
public class LongestSumSubArrayLengthInPositiveArray {
    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int L = 0;
        int R = 0;
        int sum = arr[0];
        int len = 0;
        while (R < arr.length) {//全是正数才能这样，否则只能用hashMap
            if (sum == aim) {//等于目标时记录数组大小，左边往右
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            } else if (sum < aim) {//小于目标时右扩
                R++;
                if (R == arr.length) {//边界条件
                    break;
                }
                sum += arr[R];
            } else {//大于目标时左扩
                sum -= arr[L++];
            }
        }
        return len;
    }

    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));

    }

}
