package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 区间修改，区间查询
 * <p>
 * 给定一个大小为 M*N 的零矩阵，直到输入文件结束，你需要进行若干个操作，操作有两类：
 * 1。 a b c d x，表示将左上角为 (a.b)，右下角为 (c,d) 的子矩阵全部加上 x；
 * 2。 a b c d，表示询问左上角为 (a,b)，右下角为 (c,d) 为顶点的子矩阵的所有数字之和。
 * @Date: 2022/1/18
 */

public class _2DimBinaryIndexTree3 {
    public static int N = 5005;
    public static long n, m, q;
    public static long[][] a1 = new long[N][N], a2 = new long[N][N], a3 = new long[N][N], a4 = new long[N][N];

    public static long lowbit(long x) {
        return x & -x;
    }

    public static void add(int x, int y, long z) {
        for (int i = x; i <= n; i += lowbit(i)) {
            for (int j = y; j <= m; j += lowbit(j)) {
                a1[i][j] += z;
                a2[i][j] += z * x;
                a3[i][j] += z * y;
                a4[i][j] += z * x * y;
            }
        }
    }

    public static void range_add(int xa, int ya, int xb, int yb, long z) {
        add(xa, ya, z);
        add(xa, yb + 1, -z);
        add(xb + 1, ya, -z);
        add(xb + 1, yb + 1, z);
    }

    public static long sum(int x, int y) {
        long res = 0;
        for (int i = x; i != 0; i -= lowbit(i))
            for (int j = y; j != 0; j -= lowbit(j))
                res += (x + 1) * (y + 1) * a1[i][j] - (y + 1) * a2[i][j] - (x + 1) * a3[i][j] + a4[i][j];
        return res;
    }

    public static long range_sum(int xa, int ya, int xb, int yb) {
        return sum(xb, yb) - sum(xb, ya - 1) - sum(xa - 1, yb) + sum(xa - 1, ya - 1);
    }

//    int main() {
//        cin >> n >> m;
//        ll op;
//
//        while (cin >> op) {
//            if (op == 1) {
//                ll a, b, c, d, e;
//                cin >> a >> b >> c >> d >> e;
//                range_add(a, b, c, d, e);
//            } else {
//                ll a, b, c, d;
//                cin >> a >> b >> c >> d;
//                cout << range_sum(a, b, c, d) << endl;
//            }
//        }
//    }
}
