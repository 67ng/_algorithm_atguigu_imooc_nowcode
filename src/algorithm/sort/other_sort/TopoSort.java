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
