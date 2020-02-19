package _courses.nowcode.zuoshen.sort;

import java.util.Arrays;

import static _courses.nowcode.zuoshen.sort.InsertionSort.swap;

/**
 * Description:
 *
 * @author: 黑山老妖
 * @date: 2018/10/13 17:38
 */
//            荷兰问题改进的快速排序
//            时间复杂度O(N*logN)，额外空间复杂度O(logN)
public class QuickSort {
    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    public static void quickSort(int[] arr, int L, int R){
        if (L < R){
            //num随机选择，防止差数据，即随机快排
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
//            记录p值需要额外的logN的空间
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }
    public static int[] partition(int[] arr, int L, int R){
        int less = L - 1;
        int more = R;
        while (L < more){
            if (arr[L] < arr[R]){//arr[R]即按最后一个位置划分
                swap(arr, ++less, L++);
            }else if (arr[L] > arr[R]){
                swap(arr, --more, L);
            }else {
                L++;
            }
        }
        swap(arr, more, R);//将R位置的值放入划分域
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] ints = {2,1,2,3,5,4};
        quickSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
