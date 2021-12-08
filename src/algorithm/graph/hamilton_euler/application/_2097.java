package algorithm.graph.hamilton_euler.application;

import java.util.*;

/**
 * @Description: 2097. 合法重新排列数对
 * 给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。
 * 如果 pairs 的一个重新排列，满足对每一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。
 * 请你返回 任意一个 pairs 的合法重新排列。
 * 注意：数据保证至少存在一个 pairs 的合法重新排列。
 * @Date: 2021/12/8
 */

public class _2097 {

    /**
     * 有向图的欧拉路径
     */
    Map<Integer, LinkedList<Integer>> graph = new HashMap<>();// 记录有向图
    LinkedList<Integer> reverse = new LinkedList<>();

    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Integer> netOut = new HashMap<>();// 记录纯入度
        for (int[] pair : pairs) {
            netOut.put(pair[1], netOut.getOrDefault(pair[1], 0) + 1);
            netOut.put(pair[0], netOut.getOrDefault(pair[0], 0) - 1);
            graph.putIfAbsent(pair[0], new LinkedList<>());
            graph.get(pair[0]).add(pair[1]);
        }

        int start = pairs[0][0];// 默认选择一个起点
        for (Map.Entry<Integer, Integer> entry : netOut.entrySet())
            if (entry.getValue() == -1)
                start = entry.getKey();// 纯出度为1的作为起点

        // 递归
        dfs(start);

        //迭代写法,使用栈
        iteration(start);

        int n = pairs.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i][0] = reverse.pollLast();
            ans[i][1] = reverse.peekLast();
        }
        return ans;
    }

    private void dfs(int start) {
        LinkedList<Integer> list = graph.get(start);
        while (list != null && !list.isEmpty()) {
            int next = list.pollLast();
            dfs(next);
        }
        reverse.add(start);// 当无路可走时就把当前边放进结果里
    }


    public void iteration(int cur) {
        Stack<Integer> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            LinkedList<Integer> adj = graph.get(cur);
            if (adj != null && adj.size() != 0) {//当前顶点的度数不为0还有路可走
                stack.push(cur);
                LinkedList<Integer> list = graph.get(cur);
                cur = list.pollLast();
            } else {//无路可走就是找到一个环
                reverse.add(cur);
                cur = stack.pop();//回退，直到退到一个顶点度数不为0的顶点，进入前面的if继续寻找新环
            }
        }
    }
}
