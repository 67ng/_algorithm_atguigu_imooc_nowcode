package data_structure.tree.special_tree.pseudo_tree.application;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 5970. 参加会议的最多员工数
 * 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 * n == favorite.length
 * 2 <= n <= 10^5
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 * 来源：力扣（LeetCode）
 * @Linked: https://leetcode-cn.com/problems/maximum-employees-to-be-invited-to-a-meeting/
 * @Date: 2022/1/3
 */

public class Solution1 {
    /**
     * 如果从 i 向 favorite[i] 连边，由题意知这是一个内向基环树森林
     * 1.当基环大小大于 2，结果就是环的大小，原因如下：
     * 树枝上的点无法插入基环
     * 树枝上的点也无法单独成环，这样会存在一个出度为 0 的节点
     * 2.当基环大小等于 2，结果就是基环左右两侧的最长链之和加上环长
     */
    public int maximumInvitations(int[] g) { // favorite 就是内向基环森林 g
        int n = g.length;
        int[] deg = new int[n]; // g 上每个节点的入度
        for (int w : g) ++deg[w];
        int[] max_depth = new int[n];

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; ++i)
            if (deg[i] == 0)//添加拓扑排序的起点
                q.add(i);

        while (!q.isEmpty()) {  // 拓扑排序，剪掉 g 上的所有树枝
            int v = q.poll();
            ++max_depth[v];
            int w = g[v]; // v 只有一条出边
            max_depth[w] = Math.max(max_depth[w], max_depth[v]);
            if (--deg[w] == 0) q.add(w);
        }

        int max_ring_size = 0, sum_chain_size = 0;
        for (int i = 0; i < n; ++i) {
            if (deg[i] == 0) continue;
            // 遍历基环上的点（拓扑排序后入度大于 0）
            deg[i] = 0;
            int ring_size = 1;
            for (int v = g[i]; v != i; v = g[v]) {
                deg[v] = 0; // 将基环上的点的入度标记为 0，避免重复访问
                ++ring_size;
            }
            if (ring_size == 2) { // 基环大小为 2
                sum_chain_size += max_depth[i] + max_depth[g[i]] + 2; // 累加两条最长链的长度
            } else {
                max_ring_size = Math.max(max_ring_size, ring_size); // 取所有基环的最大值
            }
        }
        return Math.max(max_ring_size, sum_chain_size);
    }
}
