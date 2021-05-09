package algorithm.math.matrix;

/**
 * @Description: 矩阵快速幂求斐波拉契数列
 * https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode-solution-o4ze/
 * @Created by: matreeix
 * @Date: 2021/5/9
 */
public class QuickMul {
    public int fib(int n) {
        if (n < 2) return n;
        int[][] q = {{1, 1}, {1, 0}};//求转移矩阵，难点在这！
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    //矩阵的n次方，快速幂，O(log n)
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    //矩阵乘法,O(1)
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 更多矩阵快速幂的应用参考：
     * https://www.acwing.com/blog/content/577/
     * https://www.acwing.com/blog/content/2311/
     * */
}
