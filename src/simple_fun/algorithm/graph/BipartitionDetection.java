package simple_fun.algorithm.graph;

/**
 * 二分图的检测
 * 核心思想：通过DFS分别对相邻顶点染不同的颜色，若推出染出相邻顶点同色就不是二分图
 */

public class BipartitionDetection {

    private Graph G;

    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            colors[i] = -1;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v))
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v])
                return false;
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public int[] colors(){ return colors;}

}
