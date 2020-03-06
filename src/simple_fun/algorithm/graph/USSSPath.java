package simple_fun.algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
/**
 * BFS的重要性质：按距离顺序遍历所有点
 * */
// Unweighted Single Source Shortest Path
public class USSSPath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;//记录到起始点的距离

    public USSSPath(Graph G, int s) {

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
            dis[i] = -1;
        }
        bfs(s);

        for (int i = 0; i < G.V(); i++)
            System.out.print(dis[i] + " ");
        System.out.println();
    }

    private void bfs(int s) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;//距离加一
                }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    public int dis(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    public Iterable<Integer> path(int t) {

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

}
