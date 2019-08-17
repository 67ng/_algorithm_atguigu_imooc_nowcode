package nowcode.zuoshenjinjie.map;

import java.util.HashMap;

/**
 * Description:给定一个数组arr，和一个整数num，求在arr中，累加和等于num的最长子数组的长度
 * 例子：
 * arr = {7,3,2,1,1,7,7,7} num = 7
 * 其中有很多的子数组累加和等于7，但是最长的子数组是{3,2,1,1}，所
 * 以返回其长度4
 *
 * @author: NULL
 * @date: 2018/10/30 10:10
 */
public class LongestSumSubArrayLength {
    public static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // important，(sum - aim)定义的初始位置
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                len = Math.max(i - map.get(sum - aim), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    private static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arr = generateArray(20);
        int[] arr = {7,3,2,1,1,-6,-1,7};
        printArray(arr);
        System.out.println(maxLength(arr, 7));
    }

}
