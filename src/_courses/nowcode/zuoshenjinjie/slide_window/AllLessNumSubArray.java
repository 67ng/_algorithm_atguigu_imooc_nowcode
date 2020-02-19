package _courses.nowcode.zuoshenjinjie.slide_window;

import java.util.LinkedList;

/**
 * Description:最大值和最小值差值不大于num的子数组数量
 * 时间复杂度O（N）
 *
 * @author: NULL
 * @date: 2018/10/26 20:23
 */
public class AllLessNumSubArray {
    //暴力解法,时间复杂度O(N^3)
    public static int getNum1(int[] arr, int num) {
        int res = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                if (isValid(arr, start, end, num)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }
//     窗口的O(N)解法
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int L = 0;//窗口左边界
        int R = 0;//窗口右边界
        int res = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);//最小值窗口
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);//最大值窗口
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;//满足条件时跳出循环
                }
                R++;//不满足条件时向右扩大
            }
            //若窗口里的数小于左边界就弹出
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            res += R - L;//满足条件且包含左边界的子数组的数量
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 5, 5, 6, 9, 10};
        System.out.println(getNum(test, 2));
        System.out.println(getNum1(test, 2));
    }
}
