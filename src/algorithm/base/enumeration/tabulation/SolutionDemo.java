package algorithm.base.enumeration.tabulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: 2081. k 镜像数字的和
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。
 * 比方说，9 是一个 2 镜像数字。9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 * 相反地，4 不是一个 2 镜像数字。4 在二进制下为 100 ，从前往后和从后往前读不相同。
 * 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。
 * 数据范围：
 * 2 <= k <= 9
 * 1 <= n <= 30
 * @Date: 2021/11/25
 */

public class SolutionDemo {
    public long kMirror(int k, int n) {
        long[][] tables = getTable2();
        long[] table = tables[k - 2];
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += table[i];
        }
        return res;
    }

    //检查是否回文串
    private static boolean isPalindromeStr(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 检查是否回文数
    public static boolean isPalindromeNum(long x) {
        if (x % 10 == 0) return false;
        long reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10L + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }

    // 求给定整数的下一个回文数
    public static long nextPalindromeNum(long num) {
        char[] chars = Long.toString(num).toCharArray();
        int len = chars.length;
        for (int i = len / 2; i >= 0; i--) {
            if (chars[i] != '9') {
                chars[i]++;
                if (len - 1 - i != i) {
                    chars[len - 1 - i]++;
                }
                for (int j = i + 1; j <= len / 2; j++) {
                    chars[j] = '0';
                    chars[len - 1 - j] = '0';
                }
                return Long.parseLong(new String(chars));
            }
        }
        long ans = 1;
        for (int i = 0; i < len; i++) ans *= 10;
        ans += 1;
        return ans;
    }

    // 求所有进制
    public static List<List<Long>> work(long start, long end) {
        List<List<Long>> res = new ArrayList<>();
        for (int i = 0; i < 8; i++) res.add(new ArrayList<>());
        int[] base = {2, 3, 4, 5, 6, 7, 8, 9};
        for (long i = start; i <= end; i++) {
            if (isPalindromeNum(i)) {
                for (int b : base) {
                    String s = Long.toString(i, b);
                    if (isPalindromeStr(s)) {
                        res.get(b - 2).add(i);
                    }
                }
            }
        }
        return res;
    }

    // 求指定进制
    public static List<Long> work(long start, long end, int radix) {
        List<Long> res = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            if (isPalindromeNum(i)) {
                String s = Long.toString(i, radix);
                if (isPalindromeStr(s)) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    // 暴力打表
    public static long[][] getTable1(long MAX) {
        long[][] res = new long[8][30];
        int[] base = {2, 3, 4, 5, 6, 7, 8, 9};
        int[] idx = new int[8];
        for (long i = 1; i < MAX; i++) {
            if (isPalindromeNum(i)) {
                for (int b : base) {
                    String s = Long.toString(i, b);
                    if (res[b - 2][29] == 0 && isPalindromeStr(s)) {
                        res[b - 2][idx[b - 2]++] = i;
                    }
                }
                boolean canBreak = true;// 找到所有结果就提前退出
                for (long[] b : res) {
                    if (b[29] == 0) {
                        canBreak = false;
                        break;
                    }
                }
                if (canBreak) break;
            }
        }
        return res;
    }

    // 优化check函数的打表
    public static long[][] getTable2() {
        long[][] res = new long[8][30];
        int[] base = {2, 3, 4, 5, 6, 7, 8, 9};
        int[] idx = new int[8];

        for (long i = 1; ; ) {
            for (int b : base) {
                String s = Long.toString(i, b);
                if (res[b - 2][29] == 0 && isPalindromeStr(s)) {
                    res[b - 2][idx[b - 2]++] = i;
                }
            }
            boolean canBreak = true;// 找到所有结果就提前退出
            for (long[] b : res) {
                if (b[29] == 0) {
                    canBreak = false;
                    break;
                }
            }
            if (canBreak) break;
            i = nextPalindromeNum(i);
        }
        return res;
    }

    // fork/join打表
    static class ForkJoinDemo extends RecursiveTask<List<List<Long>>> {
        public static final int THRESHOLD = 10_000_000;
        public long start;
        public long end;

        public ForkJoinDemo(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected List<List<Long>> compute() {
            if (end - start <= THRESHOLD) {
                return work(start, end);
            }
            // 分解任务
            long middle = (end + start) / 2;

            ForkJoinDemo subtask1 = new ForkJoinDemo(start, middle);
            ForkJoinDemo subtask2 = new ForkJoinDemo(middle, end);
            invokeAll(subtask1, subtask2);
            List<List<Long>> subresult1 = subtask1.join();
            List<List<Long>> subresult2 = subtask2.join();
            int size = subresult1.size();
            for (int i = 0; i < size; i++) {// 合并结果集
                subresult1.get(i).addAll(subresult2.get(i));
            }
            return subresult1;
        }

    }

    public static void main(String[] args) {
        long MAX = 10_000_000_000L;
        // 单线程执行任务
        long start = System.currentTimeMillis();
//        long[][] res = getTable1(MAX);
        long[][] res = getTable2();

        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "ms");
        for (long[] b : res) {
            System.out.println(Arrays.toString(b));
        }

        // 多线程执行任务
        ForkJoinPool fjp = new ForkJoinPool(8); // 最大并发数8
        ForkJoinTask<List<List<Long>>> task = new ForkJoinDemo(1, MAX);
        long startTime = System.currentTimeMillis();
        List<List<Long>> result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时: " + (endTime - startTime) + " ms.");
        int i = 2;
        for (List<Long> l : result) {
            System.out.println(i++ + "进制大小为：" + l.size());
            System.out.println(l);
        }
    }
}
