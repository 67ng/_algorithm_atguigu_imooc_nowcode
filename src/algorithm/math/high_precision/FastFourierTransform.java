package algorithm.math.high_precision;

import java.math.BigInteger;
import java.util.Random;

/**
 * @Description: 快速傅里叶变换计算大数乘法
 * @Date: 2021/12/29
 */

public class FastFourierTransform {
    public static long G = 3, Ginv = 332748118, P = 998244353;
    public static long[] flip_bin;

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int len1 = num1.length(), len2 = num2.length(), n = 1, N = 1;
        while ((n <<= 1) < (len1 + len2)) ++N;
        flip_bin = new long[n];
        for (int i = 0; i < n; ++i)
            flip_bin[i] = (flip_bin[i >> 1] >> 1) | ((i & 1) << (N - 1));

        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < len1; ++i) a[i] = num1.charAt(len1 - 1 - i) - '0';
        for (int i = 0; i < len2; ++i) b[i] = num2.charAt(len2 - 1 - i) - '0';
        ntt(a, false);
        ntt(b, false);
        for (int i = 0; i < n; ++i) a[i] = (a[i] * b[i]) % P;
        ntt(a, true);

        long inv = fastPow(n, P - 2);
        String res = "";
        long prev = 0;
        for (int i = 0; i < len1 + len2 - 1; ++i) {
            long curr = a[i] * inv % P + prev;
            res = (char) (curr % 10 + '0') + res;
            prev = curr / 10;
        }
        while (prev != 0) {
            res = (char) (prev % 10 + '0') + res;
            prev /= 10;
        }
        return res;
    }

    public static long fastPow(long a, long n) {
        long res = 1;
        do {
            if ((n & 1) != 0) res = (res * a) % P;
            a = (a * a) % P;
        } while ((n >>= 1) != 0);
        return res % P;
    }

    public static void ntt(long[] a, boolean inv) {
        int n = a.length;
        for (int i = 0; i < n; ++i)
            if (i < flip_bin[i])
                swap(a, i, (int) flip_bin[i]);
        for (int bisector = 1; bisector < n; bisector <<= 1) {
            long ai = fastPow(inv ? Ginv : G, (P - 1) / (bisector << 1));
            for (int i = 0; i < n; i += (bisector << 1)) {
                long multiplier = 1;
                for (int j = 0; j < bisector; ++j) {
                    long a0 = a[i + j], a1 = (multiplier * a[i + j + bisector]) % P;
                    a[i + j] = (a0 + a1) % P;
                    a[i + j + bisector] = (P + a0 - a1) % P;
                    multiplier = (multiplier * ai) % P;
                }
            }
        }
    }

    public static void swap(long[] a, int i, int j) {
        long tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    // 普通高精度乘法
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Random r = new Random();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            s1.append(Math.abs(r.nextLong()));
            s2.append(Math.abs(r.nextLong()));
        }
        System.out.println(s1.length());
        System.out.println(s2.length());
        long t1 = System.currentTimeMillis();
        String s = (new FastFourierTransform()).multiply(s1.toString(), s2.toString());
        long t2 = System.currentTimeMillis();
        BigInteger b = new BigInteger(s1.toString());
        b = b.multiply(new BigInteger(s2.toString()));

        long t3 = System.currentTimeMillis();
        System.out.println("fft:" + (t2 - t1) / 1000.0 + "s");// 44.757s
        System.out.println("BigInteger:" + (t3 - t2) / 1000.0 + "s");//3.408s

        long t4 = System.currentTimeMillis();
        String ss = (new FastFourierTransform()).multiply2(s1.toString(), s2.toString());
        long t5 = System.currentTimeMillis();

        System.out.println("highpre:" + (t5 - t4) / 1000.0 + "s");//268.94s
    }
}
