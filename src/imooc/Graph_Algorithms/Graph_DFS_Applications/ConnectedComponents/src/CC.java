package imooc.Graph_Algorithms.Graph_DFS_Applications.ConnectedComponents.src;

import java.util.ArrayList;

/**
 * 得到各个联通分量的顶点
 * */
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0;//联通分量编号

    public CC(Graph G) {

        this.G = G;
        visited = new int[G.V()];
        //赋初始值-1
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;//根据联通分量的编号区分顶点属于不同分量
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, ccid);
    }

    public int count() {
        for (int e : visited)
            System.out.print(e + "，");
        System.out.println();
        return cccount;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\mzh\\IdeaProject\\_justforfun\\src\\imooc\\Graph_Algorithms\\Graph_DFS_Applications\\ConnectedComponents\\g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }
}
