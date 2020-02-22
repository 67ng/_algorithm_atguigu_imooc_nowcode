package _courses.imooc.Graph_Algorithms.Network_Flows.EdmondsKarpAlgorithmCompleted.src;

import java.util.*;

/**
 * Edmonds-Karp算法实现Ford-Fulkerson思想
 *
 * 时间复杂度：
 *      Ford-Fulkerson思想: O(maxflow*E)
 *      Edmonds-Karp算法: O（V*E*E）
 */
public class MaxFlow {

    private WeightedGraph network;
    private int s, t;

    private WeightedGraph rG;
    private int maxFlow = 0;

    public MaxFlow(WeightedGraph network, int s, int t) {

        if (!network.isDirected())
            throw new IllegalArgumentException("MaxFlow only works in directed graph.");

        if (network.V() < 2)
            throw new IllegalArgumentException("The network should hs at least 2 vertices.");

        network.validateVertex(s);
        network.validateVertex(t);
        if (s == t)
            throw new IllegalArgumentException("s and t should be differrent.");

        this.network = network;
        this.s = s;
        this.t = t;

        this.rG = new WeightedGraph(network.V(), true);
        for (int v = 0; v < network.V(); v++)
            for (int w : network.adj(v)) {
                int c = network.getWeight(v, w);
                rG.addEdge(v, w, c);//反向边初始为权值
                rG.addEdge(w, v, 0);//正向边初始为0
            }

        while (true) {
            //1.得到一条增广路径
            ArrayList<Integer> augPath = getAugmentingPath();
            if (augPath.size() == 0) break;

            int f = Integer.MAX_VALUE;
            //2.计算增广路径上的最小值
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            maxFlow += f;//增加的流量
            //3.根据增广路径更新rG
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                rG.setWeight(v, w, rG.getWeight(v, w) - f);
                rG.setWeight(w, v, rG.getWeight(w, v) + f);
            }
        }
    }

    //通过BFS找到增广路径
    private ArrayList<Integer> getAugmentingPath() {

        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[network.V()];
        Arrays.fill(pre, -1);

        q.add(s);
        pre[s] = s;
        while (!q.isEmpty()) {
            int cur = q.remove();
            if (cur == t) break;
            for (int next : rG.adj(cur))
                if (pre[next] == -1 && rG.getWeight(cur, next) > 0) {
                    pre[next] = cur;
                    q.add(next);
                }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if (pre[t] == -1) return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public int result() {
        return maxFlow;
    }

    public int flow(int v, int w) {

        if (!network.hasEdge(v, w))
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));

        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {

        WeightedGraph network = new WeightedGraph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses\\imooc\\Graph_Algorithms\\Network_Flows\\EdmondsKarpAlgorithmCompleted\\network.txt", true);
        MaxFlow maxflow = new MaxFlow(network, 0, 3);
        System.out.println(maxflow.result());
        for (int v = 0; v < network.V(); v++)
            for (int w : network.adj(v))
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow.flow(v, w), network.getWeight(v, w)));

        WeightedGraph network2 = new WeightedGraph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses\\imooc\\Graph_Algorithms\\Network_Flows\\EdmondsKarpAlgorithmCompleted\\network2.txt", true);
        MaxFlow maxflow2 = new MaxFlow(network2, 0, 5);
        System.out.println(maxflow2.result());
        for (int v = 0; v < network2.V(); v++)
            for (int w : network2.adj(v))
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxflow2.flow(v, w), network2.getWeight(v, w)));
    }
}
