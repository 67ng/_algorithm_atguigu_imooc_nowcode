package _courses.imooc.algorithmic_interview.Time_Complexity.Recursion;
/**
 *一次递归调用,时间复杂度为O(T*depth),其中T为每个递归函数的时间复杂度,depth为递归深度
 * */
public class Main {

    // binarySearch,时间复杂度O(logn)
    private static int binarySearch(Comparable[] arr, int l, int r, int target){

        if(l > r)
            return -1;

        int mid = l + (r - l) / 2;
        if(arr[mid].compareTo(target) == 0)
            return mid;
        else if(arr[mid].compareTo(target) > 0)
            return binarySearch(arr, l, mid - 1, target);
        else
            return binarySearch(arr, mid + 1, r, target);

    }

    // sum,时间复杂度O(n)
    private static int sum(int n){

        assert n >= 0;

        if(n == 0)
            return 0;
        return n + sum(n - 1);
    }

    // pow2,时间复杂度O(logn)
    private static double pow(double x, int n){

        assert n >= 0;

        if(n == 0)
            return 1.0;

        double t = pow(x, n / 2);
        if(n % 2 == 1)
            return x * t * t;

        return t * t;
    }

    public static void main(String[] args) {

        System.out.println(sum(100));
        System.out.println(pow(2, 10));
    }
}
