package imooc.algorithmic_interview.Time_Complexity.Recursion;

/**
 * Created by liuyubobobo.
 * 多次递归调用
 */
public class Main2 {

    // f,时间复杂度O(2^n)
    private static int f(int n){

        assert( n >= 0 );

        if(n == 0)
            return 1;

        return f(n - 1) + f(n - 1);
    }

    /*
    // mergeSort,时间复杂度O(n*logn)
    private static void mergeSort(Comparable[] arr, int l, int r){

        if(l >= r)
            return;

        int mid = (l+r)/2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }
    */

    public static void main(String[] args) {

        System.out.println(f(10));
    }
}
