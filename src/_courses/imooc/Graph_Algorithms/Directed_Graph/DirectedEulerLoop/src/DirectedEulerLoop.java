package _courses.imooc.Graph_Algorithms.Directed_Graph.DirectedEulerLoop.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
/**
 *
 * 有向图的欧拉回路
 *
 * */
public class DirectedEulerLoop {

    private Graph G;

    public DirectedEulerLoop(Graph G) {
        if (!G.isDirected())
            throw new IllegalArgumentException("DirectedEulerLopp only works in directed graph");
        this.G = G;
    }

    private boolean hasEulerLoop() {
        for (int v = 0; v < G.V(); v++)
            if (G.indegree(v) != G.outdegree(v))//有向图有欧拉回路充分必要条件：所有顶点出度等于入度
                return false;
        return true;
    }

    public ArrayList<Integer> result() {

        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;

        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (g.outdegree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        Collections.reverse(res);//有向图要注意顺序
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Directed_Graph\\DirectedEulerLoop\\ug.txt", true);
        DirectedEulerLoop el = new DirectedEulerLoop(g);
        System.out.println(el.result());

        Graph g2 = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Directed_Graph\\DirectedEulerLoop\\ug2.txt", true);
        DirectedEulerLoop el2 = new DirectedEulerLoop(g2);
        System.out.println(el2.result());
    }
}
