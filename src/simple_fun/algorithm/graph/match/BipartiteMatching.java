package simple_fun.algorithm.graph.match;


import simple_fun.algorithm.graph.BipartitionDetection;
import simple_fun.algorithm.graph.Graph;
import simple_fun.algorithm.graph.WeightedGraph;
import simple_fun.algorithm.graph.network_flow.MaxFlow;

/**
 * 最大流解决二分图的匹配问题，为每条边赋权值为1，最大流就是最大匹配值
 */
public class BipartiteMatching {

    private Graph G;
    private int maxMatching;

    public BipartiteMatching(Graph G) {

        BipartitionDetection bd = new BipartitionDetection(G);
        if (!bd.isBipartite())
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");

        this.G = G;

        int[] colors = bd.colors();

        // 源点为 V, 汇点为 V + 1
        WeightedGraph network = new WeightedGraph(G.V() + 2, true);
        for (int v = 0; v < G.V(); v++) {//创建一个网络流模型
            if (colors[v] == 0)
                network.addEdge(G.V(), v, 1);//添加源点的一部分的边
            else
                network.addEdge(v, G.V() + 1, 1);//添加另一部分到汇点的边

            for (int w : G.adj(v))
                if (v < w) {//给二分图里的顶点添加边，注意边的方向
                    if (colors[v] == 0)
                        network.addEdge(v, w, 1);
                    else
                        network.addEdge(w, v, 1);
                }
        }

        MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching() {
        return maxMatching;
    }

    //是否完美匹配
    public boolean isPerfectMatching() { return maxMatching * 2 == G.V(); }


}
