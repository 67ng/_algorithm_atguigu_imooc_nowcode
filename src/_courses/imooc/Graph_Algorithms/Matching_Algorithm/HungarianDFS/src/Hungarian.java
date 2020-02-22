package _courses.imooc.Graph_Algorithms.Matching_Algorithm.HungarianDFS.src;

import java.util.*;

/**
 * 匈牙利算法，时间复杂度：O（V*E）
 */
public class Hungarian {

    private Graph G;
    private int maxMatching;
    private int matching[];
    private boolean visited[];

    public Hungarian(Graph G) {

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

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses\\imooc\\Graph_Algorithms\\Matching_Algorithm\\HungarianDFS\\g.txt");
        Hungarian hungarian = new Hungarian(g);
        System.out.println(hungarian.maxMatching());

        Graph g2 = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses\\imooc\\Graph_Algorithms\\Matching_Algorithm\\HungarianDFS\\g2.txt");
        Hungarian hungarian2 = new Hungarian(g2);
        System.out.println(hungarian2.maxMatching());
    }
}
