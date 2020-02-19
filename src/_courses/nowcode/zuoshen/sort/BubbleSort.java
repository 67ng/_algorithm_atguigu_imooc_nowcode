package _courses.nowcode.zuoshen.sort;

import java.util.Arrays;

/**
 * Description:冒泡排序
 *
 * @author: 黑山老妖
 * @date: 2018/10/12 15:04
 */
//        时间复杂度:O(n^2),和数据状况没关
public class BubbleSort {
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int end = arr.length-1;end>0;end--){
            for (int i=0;i<end;i++){
                if (arr[i] > arr[i+1])swap(arr, i, i+1);
            }
        }
    }
    public static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] test = {1,2,3,7,2134,345,123,3,19,0,4,27,6,8,5,2,57};
        bubbleSort(test);
        System.out.println(Arrays.toString(test));
    }
}
