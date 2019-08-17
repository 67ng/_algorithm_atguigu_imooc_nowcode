package nowcode.zuoshen.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/11/21 16:16
 */
public class Graph {
    public HashMap<Integer, Node> nodes;//顶点
    public HashSet<Edge> edges;//边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
