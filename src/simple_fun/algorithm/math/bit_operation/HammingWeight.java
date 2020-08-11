package simple_fun.algorithm.math.bit_operation;

/**
 * @Description: 汉明重量(计算整数二进制形式中1的个数)
 * @Author: caffebaby
 * @Date: 2020/7/2
 */

public class HammingWeight {

    //2的幂次与运算
    public int hammingWeight1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0)
                bits++;

            mask <<= 1;
        }
        return bits;
    }

    //位运算,原理:对于整数 n ，将 n 和 n - 1 做与运算，会把最后一个 1 的位变成 0
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

    public static void main(String[] args) {
        HammingWeight hw = new HammingWeight();
        long time1 = System.currentTimeMillis();
//        int start = Integer.MIN_VALUE;
//        int end = Integer.MAX_VALUE;

        int start = 0;
        int end = 10_000_000;
        //hammingWeight1: 16ms
        //hammingWeight2: 0ms
        //hammingWeight3: 0ms

        //int start = 0;
        //int end = 10_000_000;
        //hammingWeight1: 9ms
        //hammingWeight2: 51ms
        //hammingWeight3: 3ms

        //int start = 0;
        //int end = 100_000_000;
        //hammingWeight1: 15ms
        //hammingWeight2: 611ms
        //hammingWeight3: 0ms

        //int start = 0;
        //int end = 1000_000_000;
        //hammingWeight1: 16ms
        //hammingWeight2: 6567ms
        //hammingWeight3: 3ms


        for (int i = start; i < end; i++) {
            hw.hammingWeight1(i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("hammingWeight1: " + (time2 - time1) + "ms");

        long time3 = System.currentTimeMillis();
        for (int i = start; i < end; i++) {
            hw.hammingWeight2(i);
        }
        long time4 = System.currentTimeMillis();
        System.out.println("hammingWeight2: " + (time4 - time3) + "ms");

        long time5 = System.currentTimeMillis();
        for (int i = start; i < end; i++) {
            hw.hammingWeight3(i);
        }
        long time6 = System.currentTimeMillis();
        System.out.println("hammingWeight3: " + (time6 - time5) + "ms");
    }

}
