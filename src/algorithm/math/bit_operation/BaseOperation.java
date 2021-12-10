package algorithm.math.bit_operation;

/**
 * @Description: 位运算的常用基本操作
 * @Author: matreeix
 * @Date: 2020/6/11
 */
public class BaseOperation {

    /**
     * 位运算的一些性质：
     * 0.a % b = a & (b-1)
     * 1.a^b^b=a
     * 2.把第 i 位置 1：a |= (1 << i)
     * 3.把第 i 位置 0：a &= ~(1 << i)
     * 4.把第 i 位取反：a ^= (1 << i)
     * 5.读取第 i 位的值：(a >> i) & 1
     * 6.lowbit():a & -a，取出 a 中最右边一个 1 的位置
     * 7.a & b,则有：
     * (1)a*b>0(均为正或负)时,则有a&b<=Math.min(a,b),仅当a=b时取等号
     * (2)a*b==0,则有a&b==0
     * (3)a*b<0,则有a&b>=0
     * 8.a | b,则有：
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
    /**
     * Brian Kernighan 算法:
     * 每次对 num 和 num-1 之间进行按位与运算后，num 中最右边的 1 会被抹去变成 0。
     * */
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
