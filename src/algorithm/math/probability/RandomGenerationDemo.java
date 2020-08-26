package algorithm.math.probability;

import java.util.Random;

/**
 * @Description: 随机数生成模板
 * @Author: matreeix
 * @Date: 2020/8/26
 */

public class RandomGenerationDemo {

    //小随机数放大,n为上限
    public static int min2max(int n) {
        int cnt = 0, a = 10;//10是小随机数的上限
        while (a < n) {
            a *= 10;
            cnt++;
        }
        int ceiling = (a / n) * n;//拒绝采样的上限
        int res = Integer.MAX_VALUE;
        while (res > ceiling) {
            int i = cnt;
            res = randMin();
            while (i-- > 0) {
                res = 10 * (res - 1) + randMin();
            }
        }
        return (res - 1) % n + 1;
    }

    //大随机数缩小,n为上限
    public static int max2min(int n) {
        int x = 0;
        return (x = randMax()) > n ? x % n + 1 : x;
    }

    //生成[1,100]的随机整数
    private static int randMax() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    //生成[1,10]的随机整数
    private static int randMin() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    public static void main(String[] args) {
        int len = 123, frequency = 100000;
        int[] cnt = new int[len];

        for (int i = 0; i < frequency; i++) {
//            int v = randMax();//随机大数
//            int v = randMin();//随机小数
//            int v = max2min(len);//大生成小
            int v = min2max(len);//小生成大
            cnt[v - 1]++;
        }
        int max = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < cnt.length; i++) {
            max = Math.max(max, cnt[i]);
            min = Math.min(min, cnt[i]);
            System.out.println((i + 1) + ":" + cnt[i]);
        }
        System.out.println("max:" + max + ",min:" + min);
    }

}
