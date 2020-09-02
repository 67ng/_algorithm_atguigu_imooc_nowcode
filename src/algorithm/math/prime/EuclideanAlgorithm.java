package algorithm.math.prime;

/**
 * @Description: 欧几里得算法
 * <p>
 * 欧几里德算法又称辗转相除法，是指用于计算两个非负整数a，b的最大公约数。
 * @Author: matreeix
 * @Date: 2020/9/1
 */

public class EuclideanAlgorithm {

    //欧几里得算法
    public static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    //扩展欧几里得算法,求x, y，使得ax + by = gcd(a, b)
    public static int exgcd(int a, int b, int x, int y) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int d = exgcd(b, a % b, y, x);
        y -= (a / b) * x;
        return d;
    }

    public static void main(String[] args) {
        System.out.println(gcd(6,18));
    }

}
