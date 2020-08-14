package algorithm.math;

/**
 * @Description: 快速幂算法
 * Linked:https://www.acwing.com/blog/content/3109/
 * @Author: matreeix
 * @Date: 2020/7/14
 */

public class QuickMul {

    /**
     * 1. 求解问题
     * 求 a^k mod p问题，暴力方法需要 O(k)的时间复杂度，快速幂算法只要 O(logk)的时间复杂度
     * <p>
     * 2. 核心思想
     * 把 a^k转换成 a^(2^x1) * a^(2^x2) * a^(2^x3) * ... a^(2^xt)
     * 等价与把 k拆分成若干个 2^xi相乘的形式，其实就是求k的二进制表示过程 k == 0b(101001)
     */
    public static int qmi(int a, int k, int p) {
        int res = 1 % p, t = a;
        while (k > 0) {
            if ((k & 1) == 1) {
                res = res * t % p;
            }
            t = t * t % p;
            k >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 3, p = 7;
        for (int k = 0; k < 20; k++)
            if (qmi(a, k, p) != (int) Math.pow(a, k) % p)
                System.out.println("Thers is something wrong!");
        System.out.println("good!");
    }
}
