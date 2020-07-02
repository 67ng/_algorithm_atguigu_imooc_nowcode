package simple_fun.algorithm.math.bit_operation;

/**
 * @Description: 汉明重量(计算整数二进制形式中1的个数)
 * @Author: Pythagodzilla
 * @Date: 2020/7/2
 */

public class HammingWeight {
    // you need to treat n as an unsigned value
    //循环和位移动
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0)
                bits++;

            mask <<= 1;
        }
        return bits;
    }

    //位运算,原理:对于任意数字 n ，将 n 和 n - 1 做与运算，会把最后一个 1 的位变成 0
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    //分治策略，参考Integer.bitCount(n);
    public int hammingWeight3(int n) {
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);

        return n & 0x3f;
    }


}
