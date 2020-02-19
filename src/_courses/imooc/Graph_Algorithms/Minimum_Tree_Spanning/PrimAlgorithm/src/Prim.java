package _courses.imooc.Graph_Algorithms.Minimum_Tree_Spanning.PrimAlgorithm.src;

import java.util.ArrayList;

/**
 * Prim算法，时间复杂度O（V*E）
 */
public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G) {

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1) return;

        boolean[] visited = new boolean[G.V()];//以true和false作为切分
        visited[0] = true;
        for (int i = 1; i < G.V(); i++) {//有V-1次切分
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < G.V(); v++)//遍历所有顶点
                if (visited[v])
                    for (int w : G.adj(v))
                        if (!visited[w] && G.getWeight(v, w) < minEdge.getWeight())//找到v的邻边是横切边的最小值
                            minEdge = new WeightedEdge(v, w, G.getWeight(v, w));
            mst.add(minEdge);
            visited[minEdge.getV()] = true;//不要忘记置为true
            visited[minEdge.getW()] = true;
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Minimum_Tree_Spanning\\PrimAlgorithm\\g.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
