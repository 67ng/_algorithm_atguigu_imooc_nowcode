package nowcode.zuoshen.sort;


import java.util.Arrays;

import static nowcode.zuoshen.sort.InsertionSort.swap;

/**
 * Description:堆排序(优先级队列结构，就是堆结构)
 *
 * @author: 黑山老妖
 * @date: 2018/10/13 19:56
 */


public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);// 1.数组生成大根堆
        }
        int heapSize = arr.length;
        System.out.println(Arrays.toString(arr));

        swap(arr, 0, --heapSize);//2.让最大数即第一个数(堆顶)和最后的数交换，弹出到数组末尾，并重新形成大根堆
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    //    向堆中插入数据
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //   某个元素发生变化，重新调整为大根堆(下沉)
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1
                    : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

  /*  public static void main(String[] args) {
        int[] ints = {9, 2, 3, 8, 5, 6, 7, 4, 1, 10};
        heapSort(ints);

    }*/
}
