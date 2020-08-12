package simple_fun.algorithm.sort;

/**
 * @Description: 冒泡排序
 * @Author: matreeix
 * @Date: 2020/2/19
 */

public class BubbleSort {

    //简单冒泡
    public int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    Utils.swap(array, j, j + 1);
                }
        return array;
    }

    //冒泡的优化
    public int[] bubbleSortOpt(int[] arr) {

        int n = arr.length;
        int newn; // 理论上,可以使用newn进行优化,但实际优化效果较差

        do {
            newn = 0;
            for (int i = 1; i < n; i++)
                if (arr[i - 1] > arr[i]) {
                    Utils.swap(arr, i - 1, i);

                    // 可以记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    newn = i;
                }
            n = newn;
        } while (newn > 0);

        return arr;
    }

}
