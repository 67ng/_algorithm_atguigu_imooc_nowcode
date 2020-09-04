package algorithm.math.linear_program;

/**
 * @Description: 高斯消元法解决线性方程
 * @Author: matreeix
 * 链接：https://www.acwing.com/blog/content/406/
 * @Date: 2020/9/1
 */

public class GaussElimination {
    /*private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    // a[N][N]是增广矩阵
    public int gauss(int[][] a) {
        int n = a.length;
        int c, r;
        for (c = 0, r = 0; c < n; c++) {
            int t = r;
            for (int i = r; i < n; i++)   // 找到绝对值最大的行
                if (Math.abs(a[i][c]) > Math.abs(a[t][c]))
                    t = i;

            if (Math.abs(a[t][c]) < eps) continue;

            for (int i = c; i <= n; i++)
                swap(a[][i], t,r);      // 将绝对值最大的行换到最顶端
            for (int i = n; i >= c; i--)
                a[r][i] /= a[r][c];      // 将当前上的首位变成1
            for (int i = r + 1; i < n; i++)       // 用当前行将下面所有的列消成0
                if (Math.abs(a[i][c]) > eps)
                    for (int j = n; j >= c; j--)
                        a[i][j] -= a[r][j] * a[i][c];
            r++;
        }

        if (r < n) {
            for (int i = r; i < n; i++)
                if (Math.abs(a[i][n]) > eps)
                    return 2; // 无解
            return 1; // 有无穷多组解
        }

        for (int i = n - 1; i >= 0; i--)
            for (int j = i + 1; j < n; j++)
                a[i][n] -= a[i][j] * a[j][n];

        return 0; // 有唯一解
    }*/
}
