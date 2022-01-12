package data_structure.tree.classical_problem.LCA;

import java.util.Arrays;

/**
 * @Description: 倍增算法
 * @Date: 2022/1/9
 */

public class MultiplySolution {
    static int N = 100001;
    static int fa[][] = new int[N][31], cost[][] = new int[N][31], dep[] = new int[N];
    static int v[][];
    static int w[][];
    static int a, b, c;

    // dfs，用来为 lca 算法做准备。接受两个参数：dfs 起始节点和它的父亲节点。
    public static void dfs(int root, int fno) {
        // 初始化：第 2^0 = 1 个祖先就是它的父亲节点，dep 也比父亲节点多 1。
        fa[root][0] = fno;
        dep[root] = dep[fa[root][0]] + 1;
        // 初始化：其他的祖先节点：第 2^i 的祖先节点是第 2^(i-1) 的祖先节点的第
        // 2^(i-1) 的祖先节点。
        for (int i = 1; i < 31; ++i) {
            fa[root][i] = fa[fa[root][i - 1]][i - 1];
            cost[root][i] = cost[fa[root][i - 1]][i - 1] + cost[root][i - 1];
        }
        // 遍历子节点来进行 dfs。
        int sz = v[root].length;
        for (int i = 0; i < sz; ++i) {
            if (v[root][i] == fno) continue;
            cost[v[root][i]][0] = w[root][i];
            dfs(v[root][i], root);
        }
    }

    // lca。用倍增算法算取 x 和 y 的 lca 节点。
    public static int lca(int x, int y) {
        // 令 y 比 x 深。
        if (dep[x] > dep[y]) swap(dep, x, y);
        // 令 y 和 x 在一个深度。
        int tmp = dep[y] - dep[x], ans = 0;
        for (int j = 0; tmp != 0; ++j, tmp >>= 1)
            if ((tmp & 1) != 0) {
                ans += cost[y][j];
                y = fa[y][j];
            }
        // 如果这个时候 y = x，那么 x，y 就都是它们自己的祖先。
        if (y == x) return ans;
        // 不然的话，找到第一个不是它们祖先的两个点。
        for (int j = 30; j >= 0 && y != x; --j) {
            if (fa[x][j] != fa[y][j]) {
                ans += cost[x][j] + cost[y][j];
                x = fa[x][j];
                y = fa[y][j];
            }
        }
        // 返回结果。
        ans += cost[x][0] + cost[y][0];
        return ans;
    }

    private static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }


    public static void main(String[] args) {
        int[][] querys = {{0, 1}, {1, 5}, {2, 4}, {3, 6}};
        int[][] v = {{1, 2}, {0, 3}, {0, 4}};
        int[][] w = {{10}, {4}, {7}, {19}};

        // 读入树：节点数一共有 n 个。
//        for (int i = 1; i < trees.length; ++i) {
//            scanf("%d %d %d", &a, &b, &c);
//            ++a, ++b;
//            v[a].push_back(b);
//            v[b].push_back(a);
//            w[a].push_back(c);
//            w[b].push_back(c);
//        }
        // 为了计算 lca 而使用 dfs。
        dfs(1, 0);
        // 查询 m 次，每一次查找两个节点的 lca 点。
        for (int[] query : querys) {
            System.out.println(lca(query[0], query[1]));
        }

    }
}
