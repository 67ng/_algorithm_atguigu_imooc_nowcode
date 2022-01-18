package data_structure.tree.binary_tree.BIT;

/**
 * @Description: 区间修改，单点查询
 * <p>
 * 给出一个 N*M  的零矩阵 A ，你需要完成如下操作：
 * 1 a b c d k：表示左上角为 (a,b)，右下角为 (c,d) 的子矩阵内所有数都自增 k；
 * 2 x y：表示询问元素 A[x][y] 的值；
 * @Date: 2022/1/18
 */

public class _2DimBinaryIndexTree2 {
    public static int maxa = 1024 * 4 + 10; //~~pow(2,12)+10
    public static long n, m;
    public static long[][] C = new long[maxa][maxa];

    public static long lowbit(long i) {
        return i & -i;
    }

    public static void add(int a, int b, long k) {
        for (int i = a; i <= n; i += lowbit(i))
            for (int j = b; j <= m; j += lowbit(j))
                C[i][j] += k;
    }

    public static long sum(int x, int y) {
        long ans = 0;
        for (int i = x; i > 0; i -= lowbit(i))
            for (int j = y; j > 0; j -= lowbit(j))
                ans += C[i][j];

        return ans;
    }
//    int main() {
//        scanf("%lld%lld", &n, &m);
//        LL op, a, b, c, d, k, x, y;
//        memset(C, 0, sizeof C);
//
//        while (scanf("%lld", &op) != EOF) {
//            if (op == 1) {
//                scanf("%lld%lld%lld%lld%lld", &a, &b, &c, &d, &k);
//                add(a, b, k);
//                add(c + 1, d + 1, k);
//                add(c + 1, b, -k);
//                add(a, d + 1, -k);
//                //??????????add(x,k),add(y+1,-k);
//            } else if (op == 2) {
//                scanf("%lld%lld", &x, &y);
//                printf("%lld\n", sum(x, y)); //?????????
//            }
//        }
//    }
}
