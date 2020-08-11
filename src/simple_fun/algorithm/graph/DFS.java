package simple_fun.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 深度优先遍历（递归）
 * @Author: caffebaby
 * @Date: 2020/3/6
 */
public class DFS {
    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private ArrayList<Integer> order = new ArrayList<>();

    public DFS(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs1(v);
    }

    //递归
    private void dfs1(int v) {

        visited[v] = true;
        pre.add(v);//深度优先先序遍历
        for (int w : G.adj(v))
            if (!visited[w])
                dfs1(w);
        post.add(v);//深度优先后序遍历
    }

    //迭代
    private void dfs2(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            order.add(v);

            for (int w : G.adj(v))
                if (!visited[w]) {
                    stack.add(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }


}
