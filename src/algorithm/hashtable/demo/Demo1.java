package algorithm.hashtable.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Description: 840. 模拟散列表
 * 维护一个集合，支持如下几种操作：
 * <p>
 * 1.I x，插入一个数 x；
 * 2.Q x，询问数 x 是否在集合中出现过；
 * 现在要进行 N 次操作，对于每个询问操作输出对应的结果。
 * <p>
 * 输入格式
 * 第一行包含整数 N，表示操作数量。
 * 接下来 N 行，每行包含一个操作指令，操作指令为 I x，Q x 中的一种。
 * <p>
 * 输出格式
 * 对于每个询问指令 Q x，输出一个询问结果，如果 x 在集合中出现过，则输出 Yes，否则输出 No。
 * <p>
 * 每个结果占一行。
 * <p>
 * 数据范围
 * 1≤N≤10^5
 * −10^9≤x≤10^9
 * 作者：RyanL
 * 链接：https://www.acwing.com/blog/content/2083/
 * @Date: 2021/7/5
 */


/**
 * 方法1：拉链法
 * 先求哈希值，然后在哈希值为下标的数组位置存储一条链表，然后往下依次插入数据
 * 哈希表一般很少用删除操作，如果需要删除，我们写的代码也不是真正删除，而是在待删除的位置上打上标记，以表示该位置已经不可用
 * 因为元素取值是10^−9~10^9，所以求哈希值的时候(x)modN可能为负数，所以应该((x)modN+N)modN
 *
 * */
public class Demo1 {
    private static int[] hash, e, ne;
    private static int idx = 0;
    private static final int N = 100003;

    static void insert(int x) {
        //求哈希值,哈希值可能是负数，所以+N再modN
        int t = (x % N + N) % N;
        //头插法
        e[idx] = x;
        ne[idx] = hash[t];
        hash[t] = idx;
        idx++;
    }

    static boolean find(int x) {
        int t = (x % N + N) % N;
        for (int i = hash[t]; i != -1; i = ne[i]) {
            if (e[i] == x) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        hash = new int[N];
        Arrays.fill(hash, -1);
        e = new int[N];
        ne = new int[N];
        while (n-- > 0) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[1]);
            if (s[0].equals("I")) {
                insert(x);
            } else {
                if (find(x)) System.out.println("Yes");
                else System.out.println("No");
            }
        }
    }
}
