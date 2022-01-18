package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 区间修改，区间查询
 * <p>
 * 给定数列 a[1],a[2]....a[n]，你需要依次进行 q 个操作，操作有两类：
 * 1. 给定 l,r,x ，对于所有在 [l,r] 间的 i ,将 a[i] 加上 x（换言之，将 a[l],a[l+1]...a[r] 分别加上 x）；
 * 2. 给定 l,r，求 SUM = a[l]+a[l+1]+...+a[r] 的值。
 * @Date: 2022/1/18
 */

public class BinaryIndexTree3 {
    public static int maxn = 1000010;
    public static int[] a = new int[maxn], c = new int[maxn], cj = new int[maxn];
    public static int n, m;
    public static int op, x, y, k;

    public static char getchar() {
        return 'a';
    }

    public static int read() {
        int w = 1, x = 0;
        char c = getchar();

        while (c < '0' || c > '9') {
            if (c == '-') {
                w = -1;
            }
            c = getchar();
        }

        while (c >= '0' && c <= '9') {
            x = (x << 3) + (x << 1) + c - '0';
            c = getchar();
        }

        return x * w;
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public static int getsum(int x) { //a[x]前缀和
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i))
            ans += (x + 1) * c[i] - cj[i];

        return ans;
    }

    public static void add(int x, int k) {
        for (int i = x; i <= n; i += lowbit(i)) {
            c[i] += k;
            cj[i] += x * k;
        }
    }

    public static void init() {
        for (int i = 1; i <= n; i++) {
            a[i] = read();
            c[i] += a[i] - a[i - 1];
            cj[i] += i * (a[i] - a[i - 1]);
            int fa = i + lowbit(i);

            if (fa <= n) {
                c[fa] += c[i];
                cj[fa] += cj[i];
            }
        }
    }
//    signed main() {
//        scanf("%lld%lld", &n, &m);
//        init();
//
//        while (m--) {
//            op = read();
//
//            if (op == 1) {
//                x = read(), y = read(), k = read();
//                add(x, k), add(y + 1, -k);
//            } else {
//                x = read(), y = read();
//                printf("%lld\n", getsum(y) - getsum(x - 1));
//            }
//        }
//
//        return 0;
//    }

}
