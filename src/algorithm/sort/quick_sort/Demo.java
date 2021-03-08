package algorithm.sort.quick_sort;

import java.util.Arrays;

/**
 * @Description: 快排的模板
 * @Date: 2021/2/24
 */

public class Demo {

    private static void quickSort(int[] arr, int l, int r) {
        // 递归终止条件
        if (l >= r) return;

        // 取分界点
        int p = arr[l];
        int i = l - 1;
        int j = r + 1;
        while (i < j) {
            while (arr[++i] < p) ;//从左往右找第一个不小于 p 的元素
            while (arr[--j] > p) ;//从右往左找第一个不大于 p 的元素
            if (i < j) {//交换
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        //递归交换左右两个区间
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
    }


    public static void main(String[] args) {
        Demo d = new Demo();
        int[] arr = {1, 5, 4, 4, 5, 2, 3, 6, 2, 3, 0, 7, 8, 9};
        d.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
