package algorithm.math.prime;

/**
 * @Description: 素数判定
 * @Author: matreeix
 * @Date: 2020/8/26
 */

public class IsPrime {

    //用整除sqr(n)前的整数判断，可以用dp优化
    private static boolean isPrime(long number) {
        for (long i = 2; i * i <= number; i++)
            if (number % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        //打表法
        boolean[] noPrime = new boolean[1_000_000_000];

        for (int i = 2; i < noPrime.length; i++)
            if (!noPrime[i])
                for (int k = 2; i * k < noPrime.length; k++)
                    noPrime[i * k] = true;

        int cnt = 0;
        int l = 1, r = 1;
        for (int i = 1000; i < noPrime.length; i *= 10) {
            r = i;
            for (int j = l; j < r; j++)
                if (!noPrime[j]) cnt++;

            System.out.println("前" + i + "个数中素数比率为：" + cnt / (double) i);
            System.out.println("素数定理预测为：" + 1 / Math.log(i));
            l = i;
        }
    }
}
