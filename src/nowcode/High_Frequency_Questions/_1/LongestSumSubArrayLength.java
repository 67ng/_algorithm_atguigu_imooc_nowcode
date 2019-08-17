package nowcode.High_Frequency_Questions._1;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:2、给定一个数组，值可以为正、负和0，请返回累加和为给定值k的最长子数组长度。
 *
 * @date: 2019/3/4 10:41
 */
public class LongestSumSubArrayLength {
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();//Map<sum,index>,其中index是累加和为sum第一次出现的索引
        map.put(0, -1); // important，防止k等于arr[0]时被忽略
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if (map.containsKey(sum - k)) {//k为两个sum的差值
                len = Math.max(i - map.get(sum - k), len);//更新len的值
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
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
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));

    }
}
