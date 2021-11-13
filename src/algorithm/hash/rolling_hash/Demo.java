package algorithm.hash.rolling_hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 字符串前缀哈希模板
 * <p>
 * 字符串哈希可以解决一些kmp都难以解决的问题，例如判断两个区间内的子串是否相同
 * 例如A的哈希值为1，B为2，C为3，D为4，则ABCD可以表示成1⋅p^3+2⋅p^2+3⋅p^1+4⋅p^0
 * 如果知道了AB的哈希值为1⋅p^1+2⋅p^0，怎么求CD的哈希值呢？
 * CD的哈希值就应该是3⋅p^1+4⋅p^0，可以发现这是ABCD哈希值中的一部分，我们需要把AB的哈希值乘上p^2，再把两者相减，就能获得CD的哈希值了
 * <p>
 * 注意：
 * 1.不能映射为0，如果A的哈希值为0，那么A的哈希值也为0，冲突
 * 2.根据经验，p=131或p=13331，且Q=2^64时，几乎不会发生冲突
 * @Date: 2021/7/5
 */


/**
 * 841. 字符串哈希
 * 给定一个长度为 n 的字符串，再给定一个长度为 m 的询问query数组，每个询问包含四个整数 l1,r1,l2,r2，请你判断 [l1,r1] 和 [l2,r2] 这两个区间所包含的字符串子串是否完全相同。
 * 字符串中只包含大小写英文字母和数字。
 */
public class Demo {
    private static final int N = 100010;
    private static long[] hash = new long[N];//存储字符串哈希值
    private static long[] p = new long[N];//求p的几次方
    private static final long Q = Long.MAX_VALUE;

    static long get(int l, int r) {
        return hash[r] - hash[l - 1] * p[r - l + 1];
    }

    public static List<String> isMatch(int n, String s, int[][] query) {
        List<String> res = new ArrayList<>();
        int m = query.length;
        p[0] = 1;
        char[] c = s.toCharArray();
        char[] str = new char[N];
        System.arraycopy(c, 0, str, 1, c.length);
        for (int i = 1; i <= n; i++) {
            hash[i] = (hash[i - 1] * 13331 + str[i]) % Q;
            p[i] = p[i - 1] * 13331;
        }
        int l1, r1, l2, r2;
        for (int i = 0; i < m; i++) {
            l1 = query[i][0];
            r1 = query[i][1];
            l2 = query[i][2];
            r2 = query[i][3];
            if (get(l1, r1) == get(l2, r2))
                res.add("Yes");
            else
                res.add("No");
        }
        return res;
    }

}
