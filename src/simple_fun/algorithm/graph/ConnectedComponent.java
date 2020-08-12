package simple_fun.algorithm.graph;

import java.util.ArrayList;

/**
 * @Description: 联通分量
 * @Author: matreeix
 * @Date: 2020/3/6
 */
public class ConnectedComponent {
    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public ConnectedComponent(Graph G) {

        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }

    public int count() {
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    //显示所有联通分量及包含的顶点
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++)
            res[i] = new ArrayList<Integer>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }
}
