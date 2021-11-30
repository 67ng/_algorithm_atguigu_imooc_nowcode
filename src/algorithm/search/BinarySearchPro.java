package algorithm.search;

/**
 * @Description: 二分查找的拓展
 * @Date: 2021/11/14
 */

public class BinarySearchPro {
    // 查找第一个等于target的索引
    public static int getFirstBS(int[] arr, int l, int r, int target) {
        return getCeilingBS(arr, l, r, target);
    }

    // 查找最后一个等于target的索引
    public static int getLastBS(int[] arr, int l, int r, int target) {
        return getFloorBS(arr, l, r, target);
    }

    // 查找等于target的数的范围索引
    public static int[] getRangeBS(int[] arr, int l, int r, int target) {
        int L = getCeilingBS(arr, l, r, target);
        int R = getFloorBS(arr, l, r, target);
        return new int[]{L, R};
    }

    // 查找最后一个<=target的数的索引
    public static int getFloorBS(int[] arr, int L, int R, int target) {
        int l = L, r = R + 1;// [l,r)左闭右开
        while (l < r - 1) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (l != 0 && arr[l - 1] == target) return l - 1;
        return -1;
    }

    // 查找第一个>=target的数的索引
    public static int getCeilingBS(int[] arr, int L, int R, int target) {
        int l = L, r = R + 1;// [l,r)左闭右开
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (l != r && arr[l] == target) return l;
        return -1;
    }

    // 查找小于等于 target 的最后一个元素
    public static int getFloorBS2(int[] A, int target) {
        int l = 0, right = A.length - 1;
        while (l < right) {
            int mid = (l + right + 1) / 2;
            if (A[mid] <= target)
                l = mid;
            else
                right = mid - 1;
        }
        return l;
    }

    // 查找大于等于 target 的第一个元素
    public static int getCeilingBS2(int[] A, int target) {
        int l = 0, r = A.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (A[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // 精度浮点二分
    public static double getFloatBSByPrecision(double l, double r) {
        double eps = 1e-6;   // 表示精度，取决于题目对精度的要求
        //求保留6位就>1e-8 保留 4位就1e-6 差两位，比要求的位数多二
        while (r - l > eps) {
            double mid = (l + r) / 2;
            if (check(mid)) r = mid;
            else l = mid;
        }
        return l;
    }

    // 次数浮点二分
    public static double getFloatBSByTime(double l, double r) {
        int time = 1000;
        for (int i = 0; i < time; i++) {
            double mid = (l + r) / 2;
            if (check(mid)) r = mid;
            else l = mid;
        }
        return l;
    }

    // 检查x是否满足某种性质
    public static boolean check(double x) {
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11, 11};
//        int[] arr = {1, 1, 2, 3, 3};
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 3));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 0));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 12));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 3));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 0));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 12));
        System.out.println(getCeilingBS2(arr, 1));//0
        System.out.println(getCeilingBS2(arr, 3));//3
        System.out.println(getFloorBS2(arr, 12));//16
        System.out.println(getFloorBS2(arr, 8));//12
    }
}
