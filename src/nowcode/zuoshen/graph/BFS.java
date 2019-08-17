package nowcode.zuoshen.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:宽度优先遍历
 * 1，利用队列实现
 * 2，从源节点开始依次按照宽度进队列，然后弹出
 * 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 4，直到队列变空
 *
 * @author: NULL
 * @date: 2018/11/21 16:31
 */
public class BFS {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> map = new HashSet<>();
        queue.add(node);
        map.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!map.contains(next)) {
                    map.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
