package _cola.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 求解单源路径问题，即求解指定两点之间的路径
 */
public class SingleSourcePath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;//存储每个顶点的前置顶点

    public SingleSourcePath(Graph G, int s) {

        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        dfs(s, s);
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w, v);
    }

    //判断两点是否可达
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    //得到两点之间的路径
    public Iterable<Integer> path(int t) {
        System.out.println("pre[]: " + Arrays.toString(pre));
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
        System.out.println("0 -> 5 : " + sspath.path(5));
    }
}
