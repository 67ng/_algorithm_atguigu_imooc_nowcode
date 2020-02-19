package _courses.imooc.Graph_Algorithms.Minimum_Tree_Spanning.KruskalAlgorithmCompleted.src;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Kruskal算法：每次选择一个最短边，如果这个边没有形成环：相当于对于一个切分，选择了最短边。其实是一种贪心算法。
 * 切分定理：横切边（顶点属于不同切分）的最短边，必属于最小生成树。
 * 时间复杂度：O（ElogE）
 */
public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G) {

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1) return;//不联通

        //Kruskal
        //1.将所有边存储起来，并按权值排序
        ArrayList<WeightedEdge> edges = new ArrayList<>();//存储所有边
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v < w)//两条相同边选择一条
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
        Collections.sort(edges);//因为Kruskal算法是从最小权值开始选择，所以要排序

        //2.用并查集将连接过的顶点合并，以此来判断新的边是否组成环
        UF uf = new UF(G.V());//将顶点放入并查集
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return mst;
    }

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Minimum_Tree_Spanning\\KruskalAlgorithmCompleted\\g.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
