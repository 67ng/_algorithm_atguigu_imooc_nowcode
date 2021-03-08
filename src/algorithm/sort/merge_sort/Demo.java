package algorithm.sort.merge_sort;

import java.util.Arrays;

/**
 * @Description: 归并排序模板
 * @Date: 2021/3/8
 */

public class Demo {

    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) >> 1;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        int k = 0, i = l, j = mid + 1;

        int[] temp = Arrays.copyOfRange(arr, l, r + 1);
        while (i <= mid && j <= r)
            if (arr[i] < arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (k = 0, i = l; i <= r; ++i, ++k)
            arr[i] = temp[k];
    }

    public static void main(String[] args) {
        Demo m = new Demo();
        int[] arr = {1, 5, 4, 4, 5, 2, 3, 6, 2, 3, 0, 7, 8, 9};
        m.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
