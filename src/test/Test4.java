package test;

import java.util.Arrays;

/**
 * Description:2019年今日头条春招一次笔试第1题
 * 选取1,4,16,64且相加等于1024-N的需要的最少数个数
 *
 * @date: 2019/3/16 10:12
 */
public class Test4 {
    public int lessMoney(int N) {
        int n = 1024 - N;
        int[] arr = new int[n + 1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0;
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < 4 && i - pow(4, j) >= 0; j++)
                arr[i] = Math.min(arr[i], 1 + arr[i - pow(4, j)]);

        return arr[n];
    }

    private static int pow(int x, int n) {
        int result = 0;
        if (n == 0) {
            result = 1;
        } else {
            result = pow(x, n / 2);
            result = result * result;
            if (n % 2 != 0) {
                result = x * result;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println((new Test4()).lessMoney(200));
        System.out.println((new Test4()).lessMoney(400));
    }
}
