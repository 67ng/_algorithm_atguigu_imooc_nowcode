package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 区间修改，单点查询
 * <p>
 * 给定数列 a[1],a[2]....a[n]，你需要依次进行 q 个操作，操作有两类：
 * 1. 给定 l,r,x ，对于所有在 [l,r] 间的 i ,将 a[i] 加上 x（换言之，将 a[l],a[l+1]...a[r] 分别加上 x）；
 * 2. 给定 i，求 a[i] 的值。
 * @Date: 2022/1/18
 */

public class BinaryIndexTree2 {
    public static int maxa = 1000010;
    public static long n;
    public static long[] c = new long[maxa];

    public static long lowbit(long i) {
        return i & -i;
    }

    public static void add(int x, long y) {
        while (x <= n) {
            c[x] += y;
            x += lowbit(x);
        }
    }

    public static long sum(int x) {
        long ans = 0;
        while (x > 0) {
            ans += c[x];
            x -= lowbit(x);
        }
        return ans;
    }

//    int main() {
//        LL c, b = 0;
//        scanf("%lld", &n);
//        scanf("%lld", &q);
//
//        for (int i = 1; i <= n; i++) {
//            scanf("%lld", &c);
//            add(i, c - b);
//            b = c;
//        }
//
//        LL op, e1, e2, e3;
//
//        for (int i = 0; i < q; i++) {
//            scanf("%lld", &op);
//
//            if (op == 1) {
//                scanf("%lld", &e1);
//                scanf("%lld", &e2);
//                scanf("%lld", &e3);
//
//                if (e1 > e2)
//                    swap(e1, e2);
//
//                add(e1, e3);
//                add(e2 + 1, -e3);
//            } else {
//                scanf("%lld", &e1);
//                printf("%lld\n", sum(e1));
//            }
//        }
//
//        return 0;
//    }

}
