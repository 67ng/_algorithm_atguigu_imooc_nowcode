package simple_fun.algorithm.graph.shortest_path;

import simple_fun.algorithm.graph.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: caffebaby
 * @Date: 2020/3/12
 */
public class DijkstraMore {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;//记录最短路径

    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public DijkstraMore(WeightedGraph G, int s) {

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);//小心权值可能会溢出

        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dis[s] = 0;
        pre[s] = s;
        visited = new boolean[G.V()];

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {

            int cur = pq.remove().v;
            if (visited[cur]) continue;

            visited[cur] = true;
            for (int w : G.adj(cur))
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;//更新前置顶点
                    }
                }
        }
    }

    public boolean isConnectedTo(int v) {

        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {

        G.validateVertex(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t) {

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }
}
