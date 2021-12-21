package algorithm.graph.match;

import BipartitionDetection;
import algorithm.graph.Graph;

import java.util.Arrays;

/**
 * 匈牙利算法，时间复杂度：O（V*E）
 */
public class HungarianDFS {

    private Graph G;
    private int maxMatching;
    private int matching[];
    private boolean visited[];

    public HungarianDFS(Graph G) {

        BipartitionDetection bd = new BipartitionDetection(G);
        if (!bd.isBipartite())
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");

        this.G = G;

        int[] colors = bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching, -1);

        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (colors[v] == 0 && matching[v] == -1) {//从左侧出发，且没有在以前遍历过的增广路径上的顶点
                Arrays.fill(visited, false);//每轮都要重新dfs
                if (dfs(v)) maxMatching++;
            }
    }

    private boolean dfs(int v) {

        visited[v] = true;
        for (int u : G.adj(v))
            if (!visited[u]) {
                visited[u] = true;
                if (matching[u] == -1 || dfs(matching[u])) {
                    matching[u] = v;//匹配边和非匹配边的互换
                    matching[v] = u;
                    return true;
                }
            }
        return false;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == G.V();
    }

}
