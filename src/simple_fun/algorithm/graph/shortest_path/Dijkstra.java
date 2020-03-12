package simple_fun.algorithm.graph.shortest_path;


import simple_fun.algorithm.graph.WeightedGraph;

import java.util.Arrays;

/**
 *
 * 时间复杂度O（V^2）
 *
 * */
public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;//各个顶点到原点的距离
    private boolean[] visited;//已确定最小值的顶点

    public Dijkstra(WeightedGraph G, int s) {

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        visited = new boolean[G.V()];

        while (true) {
            int cur = -1, curdis = Integer.MAX_VALUE;
            for (int v = 0; v < G.V(); v++)//第一次只是找到原点，后面的是找到邻接点距离前一个点最近的顶点
                if (!visited[v] && dis[v] < curdis) {
                    curdis = dis[v];
                    cur = v;
                }
            if (cur == -1) break;//访问完所有顶点就退出循环

            visited[cur] = true;
            for (int w : G.adj(cur))//更新邻接顶点距离
                if (!visited[w])
                    if (dis[cur] + G.getWeight(cur, w) < dis[w])
                        dis[w] = dis[cur] + G.getWeight(cur, w);
        }
    }

    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

}
