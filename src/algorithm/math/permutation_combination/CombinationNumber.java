package algorithm.math.permutation_combination;

/**
 * @Description: 求组合数 C(a,b)
 * @Author: matreeix
 * @Date: 2020/9/1
 */

public class CombinationNumber {
    //dp
    public static int getCombinaton(int a, int b) {
        // c[a][b] 表示从a个苹果中选b个的方案数
        int N = a + 1, mod = 1000000007;
        int[][] c = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j <= i; j++)
                if (j == 0)
                    c[i][j] = 1;
                else
                    c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;//c(n,m)=c(n-1,m-1)+c(n-1,m)
        return c[a][b];
    }

    /**
     * Lucas定理:
     * 若p是质数，则对于任意整数 1 <= m <= n，有：
     * C(n, m) = C(n % p, m % p) * C(n / p, m / p) (mod p)
     * 参考链接：https://www.cnblogs.com/fzl194/p/9095177.html
     */
    private int p;

    private long qmi(int a, int k)//快速幂模板
    {
        int res = 1;
        while (k != 0) {
            if ((k & 1) != 0) res = res * a % p;
            a = a * a % p;
            k >>= 1;
        }
        return res;
    }


    private long C(int a, int b) //通过定理求组合数C(a, b)
    {
        long res = 1;
        for (int i = 1, j = a; i <= b; i++, j--) {
            res = (long) res * j % p;
            res = (long) res * qmi(i, p - 2) % p;
        }
        return res;
    }

    public int lucas(int a, int b) {
        if (a < p && b < p) return (int) C(a, b);
        return (int) C(a % p, b % p) * lucas(a / p, b / p) % p;
    }


    /**
     * 卡特兰数: 给定n个0和n个1，它们按照某种顺序排成长度为2n的序列，满足任意前缀中0的个数都不少于1的个数的序列的数量为： Cat(n) = C(2n, n) / (n + 1)
     */
    public static int getCatalanNumber(int n) {
        return getCombinaton(2 * n, n) / (n + 1);
    }

}
