package algorithm.math.high_precision;

import java.util.Arrays;

/**
 * @Description: 基本高精度计算
 * @Date: 2021/12/27
 */

public class BaseCalculation {
    //高精度加
    public static int[] add(int a[], int b[]) {// 数位倒序存储
        int len = Math.max(a.length, b.length);
        int[] res = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            res[i] += (i < a.length ? a[i] : 0) + (i < b.length ? b[i] : 0);
            if (res[i] >= 10) {
                res[i + 1] += 1;
                res[i] -= 10;
            }
        }
        return res;
    }

    //高精度减
    public static int[] sub(int a[], int b[]) {//a >= b,否则交换两者位置即可
        int len = a.length;
        int[] res = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            res[i] += a[i] - (i < b.length ? b[i] : 0);
            if (res[i] < 0) {
                res[i + 1] -= 1;
                res[i] += 10;
            }
        }
        return res;
    }

    //高精度-低精度乘,适用于b取值较小的情况
    //注：若b和10^9(或相应整型的取值上界)属于同一数量级，那么需要慎用高精度—单精度乘法。
    public static int[] mul_short(int a[], int b) {
        int[] res = new int[a.length + Integer.toString(b).length()];
        for (int i = 0; i < a.length; ++i) {
            res[i] += a[i] * b;
            if (res[i] >= 10) {
                res[i + 1] += res[i] / 10;
                res[i] %= 10;
            }
        }
        return res;
    }

    //高精度乘法
    public static int[] mul(int a[], int b[]) {
        int[] res = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b.length; j++)
                res[i + j] = a[i] * b[j];
        int t = 0;
        for (int i = 0; i < res.length; i++) {// 去除超过个数的数字
            t += res[i];
            res[i] = t % 10;
            t /= 10;
        }
        return res;
    }

    //高精度-低精度除法
    public static String div_short(char[] A, int b) {
        StringBuffer sb = new StringBuffer();
        int remain = 0;//余数
        for (int i = A.length - 1; i >= 0; i--) {
            remain = remain * 10 + A[i] - '0';
            sb.append(remain / b);
            remain %= b;
        }
        String s = sb.toString();
        int i = 0;
        for (; i < s.length() - 1; i++)
            if (s.charAt(i) != '0')
                break;
        return s.substring(i);
    }

    //高精度-高精度除法
    public static int[] div(int a[], int[] b) {
        return null;
    }

    // Karatsuba 乘法
    public static int[] karatsuba_polymul(int n, int[] a, int[] b) {
        return null;
    }

    /**
     * 压位高精度: 从 进位制 的角度理解这一过程，我们通过在 较大的进位制 下进行运算，从而达到减少参与运算的数字的位数，提升运算效率的目的。
     * */
    //加法
    public static int[] add(int a[], int b[], int p) {// 在普通高精度运算下，p=10
        int len = Math.max(a.length, b.length);
        int[] res = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            res[i] += (i < a.length ? a[i] : 0) + (i < b.length ? b[i] : 0);
            if (res[i] >= p) {
                res[i + 1] += 1;
                res[i] -= p;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a = {4, 3, 1, 2, 3, 5, 7};
        int b = 34;
        System.out.println(Arrays.toString(mul_short(a, b)));
        System.out.println(7532134 * 34L);
    }
}
