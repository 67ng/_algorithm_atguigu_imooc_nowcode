package algorithm.string.suffix_array;

/**
 * @Description: 后缀数组模板
 * @Date: 2021/7/4
 */

public class Demo {
    static int N = 1000000 + 10;
    static int height[] = new int[N], sa[] = new int[N], rak[] = new int[N], len;

    /**
     * suffix[i]: 以i为起始位置的后缀
     * sa[i]: 排名第i的后缀的起始位置
     * rak[i]: 表示suffix[i]的排名
     * height[i]: suffix(sa[i-1])和suffix(sa[i])的最长公共前缀
     * · h[i] = height[rak[i]], h[i] >= h[i-1]-1
     * · suffix[i]和suffix[j]之间的最长公共前缀 = min(height[rak[i]+1]...height[rak[j]])
     */
    private static boolean cmp(int[] y, int a, int b, int k) {
        int a1 = y[a], b1 = y[b];
        int a2 = a + k >= len ? -1 : y[a + k];
        int b2 = b + k >= len ? -1 : y[b + k];
        return a1 == b1 && a2 == b2;
    }

    static int t1[] = new int[N], t2[] = new int[N], cc[] = new int[N];

    private static void get_sa(char s[]) {
        int x[] = t1, y[] = t2, m = 200;
        for (int i = 0; i < m; i++) cc[i] = 0;
        for (int i = 0; i < len; i++) ++cc[x[i] = s[i]];
        for (int i = 1; i < m; i++) cc[i] += cc[i - 1];
        for (int i = len - 1; i >= 0; i--) sa[--cc[x[i]]] = i;
        for (int k = 1; k < len; k <<= 1) {
            int p = 0;
            for (int i = len - k; i < len; i++) y[p++] = i;
            for (int i = 0; i < len; i++) if (sa[i] >= k) y[p++] = sa[i] - k;
            for (int i = 0; i < m; i++) cc[i] = 0;
            for (int i = 0; i < len; i++) ++cc[x[y[i]]];
            for (int i = 1; i < m; i++) cc[i] += cc[i - 1];
            for (int i = len - 1; i >= 0; i--) sa[--cc[x[y[i]]]] = y[i];
            int tmp[] = y;
            x = y;
            y = tmp;
            m = 1;
            x[sa[0]] = 0;
            for (int i = 1; i < len; i++)
                x[sa[i]] = cmp(y, sa[i - 1], sa[i], k) ? m - 1 : m++;
            if (m >= len) break;
        }
    }

    private static void get_height(char s[]) {
        for (int i = 0; i < len; i++) rak[sa[i]] = i;
        int h = 0;
        height[0] = 0;
        for (int i = 0; i < len; i++) {
            if (rak[i] == 0) continue;
            int j = sa[rak[i] - 1];
            if (h != 0) h--;
            while (s[i + h] == s[j + h]) h++;
            height[rak[i]] = h;
        }
    }

    public static void main(String[] args) {
        String s = "test";
        int len = s.length();
        get_sa(s.toCharArray());
        get_height(s.toCharArray());
    }
}
