package simple_fun.algorithm.graph.hamilton_euler;

import simple_fun.algorithm.graph.*;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0, G.V());
    }

    private boolean dfs(int v, int parent, int left) {

        visited[v] = true;
        pre[v] = parent;
        left--;//存储没访问的节点数
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v;
            return true;
        }

        for (int w : G.adj(v))
            if (!visited[w]) {
                if (dfs(w, v, left)) return true;
            }
//            else if(w == 0 && left == 0){
//                end = v;
//                return true;
//            }

        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result() {

        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) return res;

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

}
