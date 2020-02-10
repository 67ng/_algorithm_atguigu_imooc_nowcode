package imooc.Graph_Algorithms.Minimum_Tree_Spanning.PrimAlgorithmOptimized.src;

import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
/**
 *
 * Prim的改进，时间复杂度O（ElogE）
 *
 * */
public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;

        boolean visited[] = new boolean[G.V()];
        visited[0] = true;
        Queue pq = new PriorityQueue<WeightedEdge>();//优先队列，默认是最小堆
        for(int w: G.adj(0))//从顶点0开始
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));

        while(!pq.isEmpty()){
            WeightedEdge minEdge = (WeightedEdge) pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])//该边俩顶点都访问过了就跳过，否则就加入mst
                continue;
            mst.add(minEdge);

            int newv = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();//选择新的顶点的逻辑
            visited[newv] = true;
            for(int w: G.adj(newv))
                if(!visited[w])//只添加未访问的顶点的边
                    pq.add(new WeightedEdge(newv, w, G.getWeight(newv, w)));
        }
    }

    public ArrayList<WeightedEdge> result(){return mst;}

    public static void main(String[] args){

        WeightedGraph g = new WeightedGraph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\imooc\\Graph_Algorithms\\Minimum_Tree_Spanning\\PrimAlgorithmOptimized\\g.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
