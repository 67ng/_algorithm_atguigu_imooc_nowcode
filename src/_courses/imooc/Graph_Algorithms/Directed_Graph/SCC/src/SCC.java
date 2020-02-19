package _courses.imooc.Graph_Algorithms.Directed_Graph.SCC.src;
/**
 * 1.在一个强联通分量中，任何两点都可达
 * <p>
 * 2.如果将所有的强联通分量看作一个点，得到的有向图一定是DAG
 *
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class SCC {

    private Graph G;
    private int[] visited;
    private int scccount = 0;

    public SCC(Graph G) {

        if (!G.isDirected())
            throw new IllegalArgumentException("SCC only works in directed graph");

        this.G = G;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);

        GraphDFS dfs = new GraphDFS(G.reverseGraph());
        ArrayList<Integer> order = new ArrayList<>();
        for (int v : dfs.post())
            order.add(v);
        Collections.reverse(order);//反图的后序遍历的逆序

        //Kosaraju算法：对每个顶点进行dfs，各个顶点将会统计到不同的强联通分量中
        for (int v : order)//order确定的顺序，不会把后面的点排到前面去
            if (visited[v] == -1) {
                dfs(v, scccount);
                scccount++;
            }
    }

    private void dfs(int v, int sccid) {

        visited[v] = sccid;
        for (int w : G.adj(v))
            if (visited[w] == -1)
                dfs(w, sccid);
    }

    public int count() {
        return scccount;
    }

    //是否强联通
    public boolean isStronglyConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    //返回所有强联通分量
    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[scccount];
        for (int i = 0; i < scccount; i++)
            res[i] = new ArrayList<Integer>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Directed_Graph\\SCC\\ug.txt", true);
        SCC scc = new SCC(g);
        System.out.println(scc.count());
        // 4

        ArrayList<Integer>[] comp = scc.components();
        for (int sccid = 0; sccid < comp.length; sccid++) {
            System.out.print(sccid + " : ");
            for (int w : comp[sccid])
                System.out.print(w + " ");
            System.out.println();
        }

//        Graph g2 = new Graph("ug2.txt", true);
//        SCC scc2 = new SCC(g2);
//        System.out.println(scc2.count());
//        // 3
//
//        Graph g3 = new Graph("ug3.txt", true);
//        SCC scc3 = new SCC(g3);
//        System.out.println(scc3.count());
//        // 1
    }
}
