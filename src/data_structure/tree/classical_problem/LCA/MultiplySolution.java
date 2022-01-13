package data_structure.tree.classical_problem.LCA;

import java.util.*;

/**
 * @Description: 倍增算法
 * 题目描述
 * 如题，给定一棵有根多叉树，请求出指定两个点直接最近的公共祖先。
 * <p>
 * 输入格式
 * 第一行包含三个正整数 N,M,S，分别表示树的结点个数、询问的个数和树根结点的序号。
 * 接下来 N-1 行每行包含两个正整数 x, y，表示 xx 结点和 y 结点之间有一条直接连接的边（数据保证可以构成树）。
 * 接下来 MM 行每行包含两个正整数 a,b，表示询问 a 结点和 b 结点的最近公共祖先。
 * <p>
 * 输出格式
 * 输出包含 M 行，每行包含一个正整数，依次为每一个询问的结果。
 * @Linked: https://www.luogu.com.cn/problem/P3379
 * @Date: 2022/1/9
 */

public class MultiplySolution {


    public static int N = 500005;
    public static Map<Integer, Set<Integer>> v = new HashMap<>();

    public static int[][] fa = new int[N][40];//fa[x][i]指从x节点向上走2^i步的节点
    public static int[] depth = new int[N];

    public MultiplySolution(TreeNode root){
        treeBuildMap(root);
        dfs(root.val, 0);
    }

    // 层序遍历的数组构建图
    private static void arrBuildMap(int[][] arrs) {
        int[] cur;
        int[] pre = null;
        for (int[] arr : arrs) {
            cur = arr;
            if (pre != null) {
                for (int j = 0; j < pre.length; j++) {
                    v.putIfAbsent(pre[j], new HashSet<>());
                    if (2 * j < cur.length) {
                        v.get(pre[j]).add(cur[2 * j]);
                        v.putIfAbsent(cur[2 * j], new HashSet<>());
                        v.get(cur[2 * j]).add(pre[j]);
                    }
                    if (2 * j + 1 < cur.length) {
                        v.get(pre[j]).add(cur[2 * j + 1]);
                        v.putIfAbsent(cur[2 * j + 1], new HashSet<>());
                        v.get(cur[2 * j + 1]).add(pre[j]);
                    }
                }
            }
            pre = cur;
        }
    }

    // 二叉树构建图
    private static void treeBuildMap(TreeNode root) {
        if (root == null) return;
        TreeNode l = root.left;
        TreeNode r = root.right;
        v.putIfAbsent(root.val, new HashSet<>());
        if (l != null) {
            v.get(root.val).add(l.val);
            v.putIfAbsent(l.val, new HashSet<>());
            v.get(l.val).add(root.val);
        }
        if (r != null) {
            v.get(root.val).add(r.val);
            v.putIfAbsent(r.val, new HashSet<>());
            v.get(r.val).add(root.val);
        }
        treeBuildMap(l);
        treeBuildMap(r);
    }

    private static void dfs(int x, int y) {//y为x的父亲
        depth[x] = depth[y] + 1;//x的深度为y+1
        fa[x][0] = y;//y为x的父亲，即x向上走2^0步
        for (int i = 1; Math.pow(2, i) <= depth[x]; i++)
            fa[x][i] = fa[fa[x][i - 1]][i - 1];//x向上走2^i步为x向上走2^(i-1)步之后向上走2^(i-1)步
        for (int w : v.get(x))
            if (w != y)
                dfs(w, x);//向下深搜
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return new TreeNode(LCA(p.val, q.val));
    }

    public static int LCA(int x, int y) {
        if (depth[x] < depth[y])
            return LCA(y, x);//保持x比y深，便于计算
        while (depth[x] > depth[y])//利用倍增法求出x的与y同深的节点。因为log2向下取整，所以可能一次消不掉故而用while。
            x = fa[x][log2(depth[x] - depth[y])];
        if (x == y) return x;//y就是x的祖先，直接返回x
        for (int i = log2(depth[x]); i >= 0; i--) {//x与y一起向上走2^i步
            if (fa[x][i] != fa[y][i]) {//注意相同可能是走过了，也可能是走到了，都按走过头了处理，因为最后差距一定只有2^0步
                x = fa[x][i];
                y = fa[y][i];
            }
        }
        return fa[x][0];//返回父亲节点
    }

    private static int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    public static void main(String[] args) {
//        int[][] trees = {{14}, {18, 15}, {27, 11, 1, 13}, {5, 30, 4, 28, 10, 16, 2, 23}, {31, 19, 25, 12, 3, 8, 9, 20, 29, 24, 17, 21, 26, 6, 7, 22}};
//        arrBuildMap(trees);
//        System.out.println(v);
//        dfs(14, 0);
//        System.out.println(LCA(19, 4));//14
//        System.out.println(lowestCommonAncestor(new TreeNode(14),new TreeNode(18),new TreeNode(15)));//14
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(6);
//        root.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(0);
//        root.right.right = new TreeNode(8);
//        root.left.right.left = new TreeNode(7);
//        root.left.right.right = new TreeNode(4);
        TreeNode res = lowestCommonAncestor(root, new TreeNode(1), new TreeNode(2));//3
        System.out.println(res.val);
    }
}
