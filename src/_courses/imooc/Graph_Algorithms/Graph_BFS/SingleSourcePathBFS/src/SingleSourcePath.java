package _courses.imooc.Graph_Algorithms.Graph_BFS.SingleSourcePathBFS.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS解决单源路径问题
 * BFS的重要性质：按距离顺序遍历所有点
 */
public class SingleSourcePath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;//存储每个顶点的前置顶点

    public SingleSourcePath(Graph G, int s) {

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++)
            pre[i] = -1;

        bfs(s);
    }

    private void bfs(int s) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : G.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
        }
    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
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

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Graph_BFS\\SingleSourcePathBFS\\g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }
}
