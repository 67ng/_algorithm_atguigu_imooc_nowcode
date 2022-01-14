package algorithm.trick.prefix_suffix_diff.application;

/**
 * @Description: 2132. 用邮票贴满网格图
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * 提示：
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 2 * 10^5
 * grid[r][c] 要么是 0，要么是 1 。
 * 1 <= stampHeight, stampWidth <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stamping-the-grid
 * @Date: 2022/1/13
 */

public class _2132 {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        int[][] diff = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                prefix[i][j] = prefix[i][j - 1] + prefix[i - 1][j] - prefix[i - 1][j - 1] + grid[i - 1][j - 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 0) {// 尝试每个空位为左上角放置邮票
                    int x = i + stampHeight, y = j + stampWidth;
                    if (x <= m && y <= n && (prefix[x][y] - prefix[x][j] - prefix[i][y] + prefix[i][j] == 0)) {
                        diff[i][j]++;
                        diff[i][y]--;
                        diff[x][j]--;
                        diff[x][y]++;
                    }
                }
        int[] cnt = new int[n + 1];
        int[] pre = new int[n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[j + 1] = cnt[j] + pre[j + 1] - pre[j] + diff[i][j];
                if (cnt[j + 1] == 0 && grid[i][j] == 0)
                    return false;
            }
            int[] tmp = cnt;
            cnt = pre;
            pre = tmp;
        }
        return true;
    }
}
