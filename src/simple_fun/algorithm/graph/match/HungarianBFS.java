package simple_fun.algorithm.graph.match;

import simple_fun.algorithm.graph.BipartitionDetection;
import simple_fun.algorithm.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HungarianBFS {

    private Graph G;
    private int maxMatching = 0;
    private int matching[];

    public HungarianBFS(Graph G) {

        BipartitionDetection bd = new BipartitionDetection(G);
        if (!bd.isBipartite())
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");

        this.G = G;

        int[] colors = bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching, -1);
        for (int v = 0; v < G.V(); v++)
            if (colors[v] == 0 && matching[v] == -1)
                if (bfs(v)) maxMatching++;
    }

    //是否有增广路径
    private boolean bfs(int v) {

        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[G.V()];
        Arrays.fill(pre, -1);

        q.add(v);
        pre[v] = v;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int next : G.adj(cur))
                if (pre[next] == -1) {
                    if (matching[next] != -1) {
                        q.add(matching[next]);
                        pre[next] = cur;
                        pre[matching[next]] = next;
                    } else {
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
        }
        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end) {

        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(start);
        return res;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == G.V();
    }

}
