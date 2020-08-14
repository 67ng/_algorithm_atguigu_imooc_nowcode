package algorithm.graph.shortest_path;

import algorithm.graph.WeightedGraph;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 *
 * 图的直径：所有点对最短路径的最大值
 * Dijkstra:O(VElogE),不能包含负权边
 * Bellman-Ford:O(E*V^2)
 * Floyed:O(V^3),可以包含负权边；检测负权环
 *
 */
public class Floyed {

    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegCycle = false;

    public Floyed(WeightedGraph G) {

        this.G = G;

        dis = new int[G.V()][G.V()];
        for (int v = 0; v < G.V(); v++)
            Arrays.fill(dis[v], Integer.MAX_VALUE);

        for (int v = 0; v < G.V(); v++) {
            dis[v][v] = 0;
            for (int w : G.adj(v))
                dis[v][w] = G.getWeight(v, w);
        }


        for (int t = 0; t < G.V(); t++)//中间点
            for (int v = 0; v < G.V(); v++)//起始点
                for (int w = 0; w < G.V(); w++)//终止点
                    if (dis[v][t] != Integer.MAX_VALUE//防止溢出
                            && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w])//松弛操作
                        dis[v][w] = dis[v][t] + dis[t][w];
        for (int v = 0; v < G.V(); v++)
            if (dis[v][v] < 0)
                hasNegCycle = true;
    }

    public boolean hasNegativeCycle() {
        return hasNegCycle;
    }

    public boolean isConnectedTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }

}
