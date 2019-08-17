package nowcode.zuoshenjinjie.slide_window;

import java.util.HashMap;

/**
 * Description:
 * <p>
 * 给定一个数组arr，值可正，可负，可0；一个整数aim，求累加
 * 和小于等于aim的，最长子数组，要求时间复杂度O(N)
 *
 * @author: NULL
 * @date: 2018/11/2 10:35
 */
public class LongestSubarrayLessSumAwesomeSolution {
    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sums = new int[arr.length];//从当前数起到末尾的最小和子数组
        int[] ends = new int[arr.length];//得到最小和的子数组末尾索引
        sums[arr.length - 1] = arr[arr.length - 1];
        ends[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
            } else {
                sums[i] = arr[i];
                ends[i] = i;
            }
        }
        int R = 0;
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            while (R < arr.length && sum + sums[R] <= aim) {//一直向右扩，直到扩不动
                sum += sums[R];
                R = ends[R] + 1;
            }
            sum -= R > i ? arr[i] : 0;//左窗口右移动一个位置，让下一个循环end指针再向右扩
            len = Math.max(len, R - i);//记录长度
            R = Math.max(R, i + 1);//更新右指针（第一个位置就扩不动，让R和i一起加一）
        }
        return len;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }

}
