package algorithm.DP.game_dp;

import java.util.Map;

/**
 * @Description: 5498. 石子游戏 V
 * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
 * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。
 * Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
 * 只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
 * 返回 Alice 能够获得的最大分数 。
 * <p>
 * 链接：链接：https://leetcode-cn.com/problems/stone-game-v/solution/on2dong-tai-gui-hua-jie-fa-by-huangyuyang/
 * @Author: matreeix
 * @Date: 2020/8/23
 */

public class StoneGameV {
    //LTE,时间复杂度O（N^3）
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stoneValue[i];

        return dp(0, n - 1, prefix);
    }

    private int dp(int i, int j, int[] prefix) {
        if (i == j) return 0;
        int res = 0;
        for (int m = i; m <= j; m++) {
            int left = prefix[m + 1] - prefix[i];
            int right = prefix[j + 1] - prefix[m + 1];
            if (left <= right)
                res = Math.max(res, dp(i, m, prefix) + left);
            if (left >= right)
                res = Math.max(res, dp(m + 1, j, prefix) + right);
        }
        return res;
    }

    //O(n^2)
    public int stoneGameV2(int[] a) {
        int n = a.length;
        int[][] s = new int[505][505];
        int[][] g = new int[505][505];
        int[][] f = new int[505][505];
        int[][] mxl = new int[505][505];
        int[][] mxr = new int[505][505];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j] = g[i][j] = s[i][j] = 0;
                mxl[i][j] = mxr[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            s[i][i] = a[i];
            g[i][i] = i;
            for (int j = i + 1; j < n; j++) {
                s[i][j] = s[i][j - 1] + a[j];
                int now = g[i][j - 1];
                while (s[i][j] - s[i][now] > s[i][now]) {
                    now++;
                }
                g[i][j] = now;
            }
        }

        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                int mid = g[l][r];
                int ls = s[l][mid];
                int rs = s[mid + 1][r];
                if (ls == rs) {
                    f[l][r] = Math.max(f[l][r], mxl[l][mid]);
                    f[l][r] = Math.max(f[l][r], mxr[mid + 1][r]);
                } else {
                    if (mid > l) {
                        ls = s[l][mid - 1];
                        f[l][r] = Math.max(f[l][r], mxl[l][mid - 1]);
                    }
                    if (mid < r) {
                        rs = s[mid + 1][r];
                        f[l][r] = Math.max(f[l][r], mxr[mid + 1][r]);
                    }
                }
                int v = f[l][r] + s[l][r];
                if (l == r) {
                    mxl[l][r] = mxr[l][r] = v;
                } else {
                    mxl[l][r] = Math.max(v, mxl[l][r - 1]);
                    mxr[l][r] = Math.max(v, mxr[l + 1][r]);
                }
            }
        }
        return f[0][n - 1];
    }

    public static void main(String[] args) {
//        int[] arr ={6,2,3,4,5,5};
        int[] arr = {1, 1, 8, 27, 64, 125, 216, 343, 512, 729, 1000, 1331, 1728, 2197, 2744, 3375, 4096, 4913, 5832, 6859, 8000, 9261, 10648, 12167, 13824, 15625, 17576, 19683, 21952, 24389, 27000, 29791, 32768, 35937, 39304, 42875, 46656, 50653, 54872, 59319, 64000, 68921, 74088, 79507, 85184, 91125, 97336, 103823, 110592, 117649, 125000, 132651, 140608, 148877, 157464, 166375, 175616, 185193, 195112, 205379, 216000, 226981, 238328, 250047, 262144, 274625, 287496, 300763, 314432, 328509, 343000, 357911, 373248, 389017, 405224, 421875, 438976, 456533, 474552, 493039, 512000, 531441, 551368, 571787, 592704, 614125, 636056, 658503, 681472, 704969, 729000, 753571, 778688, 804357, 830584, 857375, 884736, 912673, 941192, 970299};
//        System.out.println((new StoneGameV()).stoneGameV(arr));//23163347
        System.out.println((new StoneGameV()).stoneGameV2(arr));//23163347
    }
}
