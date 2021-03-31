package algorithm.search;

/**
 * @Description: 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 1.每行中的整数从左到右按升序排列。
 * 2.每行的第一个整数大于前一行的最后一个整数。
 * @Date: 2021/3/31
 */

public class MatrixBinarySearch {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / n][mid % n] < target) l = mid + 1;
            else if (matrix[mid / n][mid % n] > target) r = mid - 1;
            else return true;
        }
        return false;
    }
}
