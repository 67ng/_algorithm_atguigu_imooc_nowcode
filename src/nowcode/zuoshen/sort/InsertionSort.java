package nowcode.zuoshen.sort;

/**
 * Description:
 *每次保证前i个元素有序
 * @author: 黑山老妖
 * @date: 2018/10/12 21:05
 */
//        插入排序，时间复杂度：O(n)(有序) ~ O(n^2)(逆序)
public class InsertionSort {
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        for (int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0 && arr[j] > arr[j+1];j--){
                swap(arr, j, j+1);
                 }
            }
        }
    public static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
       /* arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];*/
    }

}
