package _cola.algorithm.graph.shortest_path;

import _cola.algorithm.graph.WeightedGraph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: 时间复杂度O（ElogE）
 * @Author: matreeix
 * @Date: 2020/3/12
 */
public class DijkstraOptimized {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;

    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {//优先队列据此方法排序
            return dis - another.dis;
        }
    }

    public DijkstraOptimized(WeightedGraph G, int s) {

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        visited = new boolean[G.V()];

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {//用优先队列找到当前未访问的dis值最小的节点

            int cur = pq.remove().v;
            if (visited[cur]) continue;//已访问的顶点跳到下一个循环

            visited[cur] = true;
            for (int w : G.adj(cur))
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
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
}
