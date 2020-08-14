package algorithm.math.random;

/**
 * @Description: 用 Rand7() 实现 Rand10()
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * 不要使用系统的 Math.random() 方法。
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * @Author: matreeix
 * @Date: 2020/8/14
 */

public class Rand7ToRand10 {

    //拒绝采样
    // 时间复杂度：期望时间复杂度为 O(1)，但最坏情况下会达到 O(∞)（一直被拒绝）。
    //空间复杂度：O(1)。
    public int rand10() {
        int a, b, idx;
        do {//可以生成7*7个随机数
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
        } while (idx > 40);//拒绝掉大于40的数
        return 1 + (idx - 1) % 10;
    }

    //优化拒绝采样
    public int newRand10() {
        int a, b, idx;
        while (true) {
            //第一次采样，得到[1,49](7*7)
            a = rand7();
            b = rand7();
            idx = b + (a - 1) * 7;
            if (idx <= 40)
                return 1 + (idx - 1) % 10;
            //第二次采样，得到[1,63](7*9)
            a = idx - 40;
            b = rand7();
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7;
            if (idx <= 60)
                return 1 + (idx - 1) % 10;
            //第三次采样，得到[1,21](3*7)
            a = idx - 60;
            b = rand7();
            // get uniform dist from 1 - 21
            idx = b + (a - 1) * 7;
            if (idx <= 20)
                return 1 + (idx - 1) % 10;
        }
    }

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     *
     * @return a random integer in the range 1 to 7
     */
    public int rand7() {//自己的实现
        return (int) (Math.random() * 7 + 1);
    }

    public static void main(String[] args) {
        Rand7ToRand10 s = new Rand7ToRand10();
        int[] count = new int[11];//统计各个数字出现频率
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            s.rand10();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("拒绝采样耗时：" + (time2 - time1));

        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            s.newRand10();
        }
        long time4 = System.currentTimeMillis();
        System.out.println("拒绝采样优化后耗时：" + (time4 - time3));

    }

}
