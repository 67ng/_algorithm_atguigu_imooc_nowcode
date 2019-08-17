package nowcode.zuoshen.graph;

import java.util.ArrayList;

/**
 * Description:节点类
 *
 * @author: NULL
 * @date: 2018/11/21 16:22
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
