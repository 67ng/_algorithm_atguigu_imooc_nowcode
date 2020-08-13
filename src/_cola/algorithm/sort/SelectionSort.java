package _cola.algorithm.sort;

/**
 * @Description: 选择排序
 * @Author: matreeix
 * @Date: 2020/2/19
 */
public class SelectionSort {

    public int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            Utils.swap(array, minIndex, i);
        }
        return array;
    }

    public int[] selectionSortOpt(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                // 比较大小
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            Utils.swap(arr, i, minIndex);
        }
        return arr;
    }

}
