package _courses.nowcode.zuoshenjinjie.map;

import java.util.HashMap;

/**
 * Description:定义数组的异或和的概念：
 * <p>
 * 数组中所有的数异或起来，得到的结果叫做数组的异或和，
 * 比如数组{3,2,1}的异或和是，3^2^1 = 0
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是：
 * 分出来的子数组中，异或和为0的子数组最多。
 * 请返回：分出来的子数组中，异或和为0的子数组最多是多少？
 * <p>
 * 注:1.交换律
 * 2.结合律（即(a^b)^c == a^(b^c)）
 * 3.对于任何数x，都有x^x=0，x^0=x
 * 4.自反性 A^B^B = A^0 = A
 * 5.若A^B = C，则A = C^B，B = C^A
 *
 * @author: NULL
 * @date: 2018/10/30 10:12
 */
//动态规划,还未理解？？？
public class Most_EOR {
    public static int mostEOR(int[] arr) {
        int ans = 0;
        int xor = 0;
        int[] dp = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 300;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostEOR(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
