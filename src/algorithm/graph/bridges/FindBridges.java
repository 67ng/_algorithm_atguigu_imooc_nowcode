package algorithm.graph.bridges;

import algorithm.graph.Edge;
import algorithm.graph.Graph;
import java.util.ArrayList;

/**
 *
 * 寻找桥或者割点只能使用DFS，不能使用BFS，因为BFS是层序遍历无法找回到祖先结点
 *
 * */
public class FindBridges {

    private Graph G;
    private boolean[] visited;

    private int ord[];//DFS遍历的顶点顺序值
    private int low[];//维护除父亲顶点外，从其他路能到达该顶点的最小order值
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];

        res = new ArrayList<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;//初始化为顺序遍历的点
        low[v] = ord[v];//初始化
        cnt++;

        for (int w : G.adj(v))
            if (!visited[w]) {
                dfs(w, v);//先遍历完图，把所有order值标好
                low[v] = Math.min(low[v], low[w]);//维护low
                if (low[w] > ord[v])//判断桥的核心代码(对于边v-w，满足low[w]>ord[v],则v-w是桥)
                    res.add(new Edge(v, w));
            } else if (w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public ArrayList<Edge> result() {
        return res;
    }

}
