package algorithm.hashtable.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Description: 方法2：开放地址法
 * 求哈希值，如果哈希值所在的位置有元素，就往后挪一位，此时有两种情况：
 * 1.哈希表中本来就有这个元素，则找到这个元素所对应的数组下标
 * 2.哈希表中本来没有这个元素，则找到对应哈希值为下标，往后找的第一个空位置存放该元素
 * 数组长度为数据总量的2到3倍，哈希冲突的概率能够降低
 * @Date: 2021/7/5
 */

public class Demo2 {
    private static int[] hash;
    private static final int N = 200003;
    private static int blank = Integer.MAX_VALUE;

    static int find(int x){
        //寻找可以插入的位置
        int t = (x % N + N) % N;
        while(hash[t] != blank && hash[t] != x){
            t++;
            if(t == N) t = 0;
        }
        return t;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        hash = new int[N];
        Arrays.fill(hash,Integer.MAX_VALUE);
        while(n-- > 0){
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[1]);
            int t = find(x);
            if(s[0].equals("I")){
                hash[t] = x;
            }else{
                if(hash[t] != blank) System.out.println("Yes");
                else System.out.println("No");
            }
        }
    }
}
