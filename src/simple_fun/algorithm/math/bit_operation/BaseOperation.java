package simple_fun.algorithm.math.bit_operation;

/**
 * @Description: 位运算的常用基本操作
 * @Author: 67ng
 * @Date: 2020/6/11
 */
public class BaseOperation {

    /**
     * 位运算的一些性质：
     * 1.a^b^b=a
     * 2.x & (-x),取到x的最后一位1-bit
     * 3.n&~1<<x,将n的第x位置为0
     *
     *
     *
     * 3.a % b = a & (b-1)
     *
     * */
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
        System.out.println(average(1234, 1236));
        System.out.println(abs(-89));
        System.out.println(power2(17));

    }


}