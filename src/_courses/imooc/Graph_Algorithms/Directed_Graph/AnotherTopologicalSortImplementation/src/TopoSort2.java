package _courses.imooc.Graph_Algorithms.Directed_Graph.AnotherTopologicalSortImplementation.src;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * DFS的后序遍历的逆序就是拓扑排序
 *
 * */
public class TopoSort2 {

    private Graph G;
    private ArrayList<Integer> res;
    private boolean hasCycle = false;

    public TopoSort2(Graph G){

        if(!G.isDirected())
            throw new IllegalArgumentException("DirectedCycleDetection only works in directed graph.");

        this.G = G;
        res = new ArrayList<>();

        hasCycle = (new DirectedCycleDetection(G)).hasCycle();
        if(hasCycle) return;

        GraphDFS dfs = new GraphDFS(G);
        for(int v: dfs.post())
            res.add(v);

        Collections.reverse(res);
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public ArrayList<Integer> result(){
        return res;
    }

    public static void main(String[] args){

        Graph ug = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Directed_Graph\\AnotherTopologicalSortImplementation\\ug.txt", true);
        TopoSort2 topoSort = new TopoSort2(ug);
        System.out.println(topoSort.result());
    }
}
