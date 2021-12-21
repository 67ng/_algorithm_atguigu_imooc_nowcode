package algorithm.graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 多源广度优先遍历
 *
 * 1765. 地图中的最高点
 * 给你一个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 和 水域 单元格组成的地图。
 *
 * 1.如果 isWater[i][j] == 0 ，格子 (i, j) 是一个 陆地 格子。
 * 2.如果 isWater[i][j] == 1 ，格子 (i, j) 是一个 水域 格子。
 * 你需要按照如下规则给每个单元格安排高度：
 *
 * 1.每个格子的高度都必须是非负的。
 * 2.如果一个格子是是 水域 ，那么它的高度必须为 0 。
 * 3.任意相邻的格子高度差 至多 为 1 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）
 * 找到一种安排高度的方案，使得矩阵中的最高高度值 最大 。
 *
 * 请你返回一个大小为 m x n 的整数矩阵 height ，其中 height[i][j] 是格子 (i, j) 的高度。如果有多种解法，请返回 任意一个 。
 *
 * @Date: 2021/2/23
 */


public class Multi_BFS {
    int[] dir_x = new int[]{ -1, 1, 0, 0 };
    int[] dir_y = new int[]{ 0, 0, -1, 1 };

    public int[][] highestPeak(int[][] isWater) {
        Queue<Integer> q = new LinkedList<>();
        int row = isWater.length, col = isWater[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isWater[i][j] == 1) {
                    isWater[i][j] = 0;
                    q.offer(i * 10000 + j);//将BFS的源头都加入队列
                } else {
                    isWater[i][j] = -1;
                }
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = cur / 10000, y = cur % 10000;
            for (int i = 0; i < 4; i++) {
                int new_x = x + dir_x[i];
                int new_y = y + dir_y[i];
                if (new_x >= 0 && new_y >= 0 && new_x < row
                        && new_y < col && isWater[new_x][new_y] == -1) {
                    isWater[new_x][new_y] = isWater[x][y] + 1;
                    q.offer(new_x * 10000 + new_y);
                }
            }
        }

        return isWater;
    }
}
