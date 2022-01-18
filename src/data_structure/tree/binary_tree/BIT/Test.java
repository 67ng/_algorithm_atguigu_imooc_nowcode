package data_structure.tree.binary_tree.BIT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Description: 测试类
 * @Date: 2022/1/18
 */

public class Test {
    // 以131. 树状数组 2 ：区间修改，单点查询为例
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int q = Integer.parseInt(tk.nextToken());

        long[] arr1 = new long[n + 1];
        long[] arr2 = new long[n + 1];
        tk = new StringTokenizer(f.readLine());
        long pre = 0;

        for (int i = 1; i <= n; i++) {
            long t = Integer.parseInt(tk.nextToken());
            add(arr1, arr2, i, t - pre);
            pre = t;
        }

        while (q-- > 0) {
            tk = new StringTokenizer(f.readLine());
            int op = Integer.parseInt(tk.nextToken());

            if (op == 1) {
                int l = Integer.parseInt(tk.nextToken());
                int r = Integer.parseInt(tk.nextToken());
                int x = Integer.parseInt(tk.nextToken());
                add(arr1, arr2, l, x);
                add(arr1, arr2, r + 1, -x);
            } else {
                int i = Integer.parseInt(tk.nextToken());
                System.out.println((i + 1) * sum(arr1, i) - sum(arr2, i) - (i * sum(arr1, i - 1) - sum(arr2, i - 1)));
            }
        }
    }

    private static long sum(long[] arr, int i) {
        long res = 0;

        for (int j = i; j > 0; j -= lowbit(j)) {
            res += arr[j];
        }

        return res;
    }

    private static void add(long[] arr1, long[] arr2, int i, long x) {
        long v = i * x;

        for (int j = i; j < arr1.length; j += lowbit(j)) {
            arr1[j] += x;
            arr2[j] += v;
        }
    }

    private static int lowbit(int x) {
        return x & -x;
    }
}
