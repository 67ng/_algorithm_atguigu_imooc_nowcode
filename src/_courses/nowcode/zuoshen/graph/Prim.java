package _courses.nowcode.zuoshen.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Description:prim算法
 *
 * 适用范围：要求无向图
 *
 * @author: NULL
 * @date: 2018/11/21 16:36
 */
public class Prim {
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(
                new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }
}
