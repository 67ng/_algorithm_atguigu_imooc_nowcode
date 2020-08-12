package simple_fun.algorithm.math.bit_operation;

/**
 * @Description: 位运算的常用基本操作
 * @Author: matreeix
 * @Date: 2020/6/11
 */
public class BaseOperation {

    /**
     * 位运算的一些性质：
     * 0.a % b = a & (b-1)
     * <p>
     * 1.a^b^b=a
     * <p>
     * 2.x & (-x),取到x的最后一位1-bit
     * <p>
     * 3.n&~1<<x,将n的 第x位 置为0
     * <p>
     * 4.a & b,则有：
     * (1)a*b>0(均为正或负)时,则有a&b<=Math.min(a,b),仅当a=b时取等号
     * (2)a*b==0,则有a&b==0
     * (3)a*b<0,则有a&b>=0
     * <p>
     * 5.a | b,则有：
     * (1)a*b>0(均为正或负)时,则有a | b>=Math.max(a,b)
     * (2)a|0==a
     * (3)a*b<0,则有a | b < 0
     */
    //判断奇偶
    public static boolean isOdd(int n) {
        return (n & 1) == 1;//true 奇数，false 偶数
    }

    //数组交换两数
    public static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    //计算平均数
    public static int average(int a, int b) {
        return (a & b) + ((a ^ b) >> 1);
    }

    //计算绝对值
    public static int abs(int n) {
        return (n + (n >> 31)) ^ (n >> 31);
    }

    //判断是否是2的正整数次幂
    public static boolean power2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
//        System.out.println(average(1234, 1236));
//        System.out.println(abs(-89));
//        System.out.println(power2(17));

//        与运算
//        for (int i = -1; i > -10000; i--) {
//            for (int j = i - 1; j > -10000; j--) {
//                if ((i & j) > i || (i & j) > j) {
//                    System.out.println("i:" + i + " j:" + j);
//                }
//
//                if ((i & j) > 0) {
//                    System.out.println("i:" + i + " j:" + j);
//                }
//            }
//        }

        //或运算
        for (int i = -1; i > -10000; i--) {
            for (int j = i - 1; j > -10000; j--) {
                if ((i | j) <= Math.max(i, j)) {
                    System.out.println("1i:" + i + " 1j:" + j);
                }
            }
        }

    }
}
