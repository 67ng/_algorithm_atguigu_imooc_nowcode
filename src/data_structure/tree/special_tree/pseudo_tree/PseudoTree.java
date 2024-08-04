package data_structure.tree.special_tree.pseudo_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 基环树（Fundamental Cycle Tree）是一种特殊的图结构，通常用于表示无向图中的所有基本环路。
 * 在无向图中，如果移除任何一条边后，图就不再包含环，则这条边属于某个基本环。基环树是由一棵生成树和不在该树中的边（称为chord或横边）组成，这些横边与生成树的边形成基本环。
 * 要实现一个基环树，我们首先需要构建一个图，然后找到一个生成树，并基于这个生成树确定所有的基本环。以下是一个简单的Java实现示例：
 * 1.定义图的基本数据结构 - 使用邻接列表表示图。
 * 2.定义节点和边的数据结构。
 * 3.寻找生成树 - 可以使用深度优先搜索（DFS）或广度优先搜索（BFS）。
 * 4.识别基本环 - 当在DFS或BFS中遇到已经访问过的节点时，可以记录下从当前节点到已访问节点的路径，这即为一个基本环。
 * @Date: 2022/1/3
 */

public class PseudoTree {
    private int V; // 节点数
    private List<List<Integer>> adj; // 邻接表
    private boolean[] visited; // 访问状态数组
    private int[] parent; // 记录节点的父节点
    private Stack<Integer> cycle; // 存储环中的节点

    public PseudoTree(int v) {
        V = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[v];
        parent = new int[v];
        cycle = new Stack<>();
    }

    // 添加无向边
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // 使用DFS查找环
    private boolean dfs(int v, int p) {
        visited[v] = true;
        parent[v] = p;

        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, v)) {
                    return true;
                }
            } else if (neighbor != p) { // 发现反向边，表明存在环
                cycle.push(v);
                for (int at = parent[v]; at != neighbor; at = parent[at]) {
                    cycle.push(at);
                }
                cycle.push(neighbor);
                return true;
            }
        }
        return false;
    }

    // 构建基环树
    public void build() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) {
                    break;
                }
            }
        }
    }

    // 打印环中的节点
    public void printCycle() {
        while (!cycle.isEmpty()) {
            System.out.print(cycle.pop() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PseudoTree ct = new PseudoTree(5);
        ct.addEdge(1, 0);
        ct.addEdge(0, 2);
        ct.addEdge(2, 1);
        ct.addEdge(0, 3);
        ct.addEdge(3, 4);

        ct.build();
        ct.printCycle(); // 输出环中的节点
    }

}
