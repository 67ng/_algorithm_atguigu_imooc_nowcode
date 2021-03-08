package algorithm.sort.merge_sort;

import java.util.Arrays;

/**
 * @Description: 自顶向下归并
 * @Author: matreeix
 * @Date: 2020/2/19
 */
public class MergeSortTD {
    private void mergeSort(int[] arr, int l, int r) {

        if (l >= r)
            return;

        int mid = (l + r) >> 1;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {// 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {// 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {// 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {// 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        MergeSortTD m = new MergeSortTD();
        int[] arr = {1, 5, 4, 4, 5, 2, 3, 6, 2, 3, 0, 7, 8, 9};
        m.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
