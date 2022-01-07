package algorithm.sort.other_sort;

import java.util.*;

/**
 * @Description: 拓扑排序
 * @Date: 2021/9/2
 */

public class TopoSort {
    static int MAX_N = 10000;
    static int[] inDegree = new int[MAX_N];
    static List<Integer>[] link = new ArrayList[MAX_N];
    static int n, m;

    static List<Integer> topologicalSort() {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0)
                queue.add(i);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            cur = -1;
            for (int i = 0; i < link[cur].size(); i++) {
                int v = link[cur].get(i);
                if (--inDegree[v] == 0)
                    queue.add(v);
            }
        }

        return res.size() == n ? res : null;// 若排序后节点小于 n，说明存在环
    }

    class Graph {
        private int v; // 顶点的个数
        private LinkedList<Integer> adj[]; // 邻接表

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t) { // s先于t，边s->t
            adj[s].add(t);
        }

        public void topoSortByKahn() {
            int[] inDegree = new int[v]; // 统计每个顶点的入度
            for (int i = 0; i < v; ++i) {
                for (int j = 0; j < adj[i].size(); ++j) {
                    int w = adj[i].get(j); // i->w
                    inDegree[w]++;
                }
            }
            LinkedList<Integer> queue = new LinkedList<>();
            for (int i = 0; i < v; ++i)
                if (inDegree[i] == 0) queue.add(i);

            while (!queue.isEmpty()) {
                int i = queue.remove();
                System.out.print("->" + i);
                for (int j = 0; j < adj[i].size(); ++j) {
                    int k = adj[i].get(j);
                    inDegree[k]--;
                    if (inDegree[k] == 0) queue.add(k);
                }
            }
        }

        public void topoSortByDFS() {
            // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
            LinkedList[] inverseAdj = new LinkedList[v];
            for (int i = 0; i < v; ++i) { // 申请空间
                inverseAdj[i] = new LinkedList<>();
            }
            for (int i = 0; i < v; ++i) { // 通过邻接表生成逆邻接表
                for (int j = 0; j < adj[i].size(); ++j) {
                    int w = adj[i].get(j); // i->w
                    inverseAdj[w].add(i); // w->i
                }
            }
            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; ++i) { // 深度优先遍历图
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, inverseAdj, visited);
                }
            }
        }

        private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
            for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
                int w = inverseAdj[vertex].get(i);
                if (visited[w]) continue;
                visited[w] = true;
                dfs(w, inverseAdj, visited);
            } // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
            System.out.print("->" + vertex);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++)
            link[i] = new ArrayList<>();
        m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            link[x].add(y);
            inDegree[y]++;
        }

        List<Integer> ans = topologicalSort();
        if (ans == null)
            System.out.println(-1);
        else
            ans.forEach(System.out::println);
    }

}
