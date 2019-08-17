package nowcode.zuoshen.logarithm;

import nowcode.zuoshen.sort.HeapSort;
import nowcode.zuoshen.sort.QuickSort;

import java.util.Arrays;

import static nowcode.zuoshen.sort.HeapSort.heapSort;
import static nowcode.zuoshen.sort.MergeSort.mergeSort;
import static nowcode.zuoshen.sort.QuickSort.quickSort;
import static nowcode.zuoshen.sort.RadixSort.radixSort;

/**
 * Description:
 *
 * @author: 黑山老妖
 * @date: 2018/10/12 21:55
 */
public class ArrayLogarithm {
    //1、实现一个绝对正确但是复杂度不好的方法B，使用的是JDK提供的Arrays类的排序方法
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    //2、实现一个随机样本产生器 
    // for test 随机数组生成器
    /*
     * Math.random() -> double[0,1)
     * (int) ((size + 1) * Math.random()) -> [0, size]
     * size = 6, size + 1 = 7
     * Math.random() -> [0,1) * 7 -> [0,7)double
     * double -> int[0,6] -> int
     */

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //产生随机数范围为[0,maxSize]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //产生[-maxValue,maxValue]的元素
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    //拷贝数组
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //3、实现比对的方法
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    //5、如果有一个样本使得比对出错，打印样本分析是哪个方法出错
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        //4、把方法a和方法b比对很多次来验证方法a是否正确 
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
//            测试的算法
//            insertionSort(arr1);
//            selectionSort(arr1);
//            mergeSort(arr1);
//            quickSort(arr1);
            radixSort(arr1);
            heapSort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);
    }

}
