package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 单点修改，区间查询
 * <p>
 * 给出一个 m*n 的零矩阵 A，你需要完成如下操作：
 * 1. x y k：表示元素 A[x][y] 自增 k；
 * 2. a b c d：表示询问左上角为 (a,b)，右下角为 (c,d) 的子矩阵内所有数的和。
 * @Date: 2022/1/18
 */

public class _2DimBinaryIndexTree1 {
    public static int N = 5000;
    public static long n;
    public static long m;
    public static long[][] sum = new long[N][N];

    public static long lowbit(long x) {
        return x & -x;
    }

    public static void add(int x, int y, long val) {
        for (int i = x; i <= n; i += lowbit(i))
            for (int j = y; j <= m; j += lowbit(j))
                sum[i][j] += val;
    }

    public static long query(int x, int y) {
        long ret = 0;
        for (int i = x; i != 0; i -= lowbit(i))
            for (int j = y; j != 0; j -= lowbit(j))
                ret += sum[i][j];
        return ret;
    }

//    int main() {
//        scanf("%lld%lld", &n, &m);
//        long long opt, x, y, x1, y1, num;
//
//        while (scanf("%lld", &opt) != EOF) {
//            if (opt == 1) {
//                scanf("%lld%lld%lld", &x, &y, &num);
//                add(x, y, num);
//            } else {
//                scanf("%lld%lld%lld%lld", &x, &y, &x1, &y1);
//                long long ans = query(x1, y1) - query(x1, y - 1) - query(x - 1, y1) + query(x - 1, y - 1);
//                printf("%lld\n", ans);
//            }
//        }
//
//        return 0;
//    }

}
