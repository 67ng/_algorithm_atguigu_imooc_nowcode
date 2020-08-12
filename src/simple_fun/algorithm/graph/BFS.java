package simple_fun.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 广度优先遍历（非递归）
 * @Author: matreeix
 * @Date: 2020/3/6
 */
public class BFS {
    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public BFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                bfs(v);
    }

    //从传入顶点开始bfs
    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);

            for (int w : G.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }


}
