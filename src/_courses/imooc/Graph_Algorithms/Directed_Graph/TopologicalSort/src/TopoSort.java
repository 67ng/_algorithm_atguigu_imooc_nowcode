package _courses.imooc.Graph_Algorithms.Directed_Graph.TopologicalSort.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * 拓扑排序
 *
 * */
public class TopoSort {

    private Graph G;

    private ArrayList<Integer> res;//拓扑排序结果
    private boolean hasCycle = false;//环检测

    public TopoSort(Graph G) {

        if (!G.isDirected())
            throw new IllegalArgumentException("TopoSort only works in directed graph.");

        this.G = G;

        res = new ArrayList<>();

        int[] indegrees = new int[G.V()];//记录入度
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            indegrees[v] = G.indegree(v);
            if (indegrees[v] == 0) q.add(v);//从入度为0的顶点开始
        }

        while (!q.isEmpty()) {
            int cur = q.remove();
            res.add(cur);

            for (int next : G.adj(cur)) {
                indegrees[next]--;
                if (indegrees[next] == 0) q.add(next);
            }
        }

        if (res.size() != G.V()) {
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public ArrayList<Integer> result() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Directed_Graph\\TopologicalSort\\ug.txt", true);
        TopoSort topoSort = new TopoSort(g);
        System.out.println(topoSort.result());
    }
}
