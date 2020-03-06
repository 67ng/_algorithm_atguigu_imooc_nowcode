package simple_fun.algorithm.graph;

import java.util.ArrayList;

/**
 * @Description: 深度优先遍历（递归）
 * @Author: 67ng
 * @Date: 2020/3/6
 */
public class DFS_Recursion {
    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public DFS_Recursion(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v);
    }

    private void dfs(int v) {

        visited[v] = true;
        pre.add(v);//深度优先先序遍历
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);
        post.add(v);//深度优先后序遍历
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }


}
