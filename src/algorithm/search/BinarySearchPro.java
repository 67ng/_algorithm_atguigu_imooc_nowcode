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

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11, 11};
//        int[] arr = {1, 1, 2, 3, 3};
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getFloorBS(arr, 0, arr.length - 1, 3));
        System.out.println(getFloorBS(arr, 0, arr.length - 1, 0));
        System.out.println(getFloorBS(arr, 0, arr.length - 1, 12));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 1));
//        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 3));
        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 0));
        System.out.println(getCeilingBS(arr, 0, arr.length - 1, 12));
    }
}
