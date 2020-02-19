package _courses.nowcode.zuoshen.graph;

/**
 * Description:边的表示
 *
 * @author: NULL
 * @date: 2018/11/21 16:17
 */
public class Edge {
    public int weight;//权值
    public Node from;//起始节点
    public Node to;//终止节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
