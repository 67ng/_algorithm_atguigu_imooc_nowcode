package algorithm.DP.digit_dp;

/**
 * @Description: 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one/
 * @Author: matreeix
 * @Date: 2020/8/22
 */

public class Count1numbers {

    //数学法，时间复杂度O(logN)
    public int countDigitOne(int n) {
        int countr = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return countr;
    }

    //数位dp
//    参考：https://leetcode-cn.com/problems/number-of-digit-one/solution/shu-wei-dp-by-weierstras/

}
