package nowcode.zuoshen.sort;

/**
 * Description:归并排序
 *
 * @author: 黑山老妖
 * @date: 2018/10/12 23:03
 */
//          时间复杂度:O(n*logn),额外空间复杂度:O(n)
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    public static void sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);//等价于(L+R)/2，防止溢出，位运算比常数运算快
        sortProcess(arr, L, mid);//L~mid有序
        sortProcess(arr, mid + 1, R);//mid~R有序
        merge(arr, L, mid, R);//让L,mid,R都有序
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        //while循环里通过外排使之有序
        while (p1 <= mid && p2 <= R) {
            //小和问题核心代码
//                res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //下面两个while有且仅有一个执行
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
