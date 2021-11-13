package algorithm.hash.rolling_hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 5803. 最长公共子路径
 * 一个国家由 n 个编号为 0 到 n - 1 的城市组成。在这个国家里，每两个 城市之间都有一条道路连接。
 * 总共有 m 个编号为 0 到 m - 1 的朋友想在这个国家旅游。他们每一个人的路径都会包含一些城市。
 * 每条路径都由一个整数数组表示，每个整数数组表示一个朋友按顺序访问过的城市序列。同一个城市在一条路径中可能 重复 出现，但同一个城市在一条路径中不会连续出现。
 * 给你一个整数 n 和二维数组 paths ，其中 paths[i] 是一个整数数组，表示第 i 个朋友走过的路径，请你返回 每一个 朋友都走过的 最长公共子路径 的长度，如果不存在公共子路径，请你返回 0 。
 * 一个 子路径 指的是一条路径中连续的城市序列。
 * @Date: 2021/7/5
 */

public class Solution {
    public int[][] paths;
    public int N = 100010;
    public int h[] = new int[N], p[] = new int[N];
    public Map<Long, Integer> cnt = new HashMap<>();
    public Map<Long, Integer> S = new HashMap<>();

    public int longestCommonSubpath(int n, int[][] pathss) {
        paths = pathss;
        n = 0;
        int l = 0, r = N;
        for (int[] p : paths) {
            n += p.length;
            r = Math.min(r, (int) p.length);
        }
        p[0] = 1;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(int mid) {
        cnt.clear();
        S.clear();
        for (int i = 0; i < paths.length; i++) {
            int[] str = paths[i];
            int n = str.length;
            for (int j = 1; j <= n; j++) {
                p[j] = p[j - 1] * 133331;
                h[j] = h[j - 1] * 133331 + str[j - 1];
            }
            for (int j = mid; j <= n; j++) {
                long t = get(j - mid + 1, j);
                if (!S.containsKey(t) || S.get(t) != i) {
                    S.put(t, i);
                    cnt.put(t, cnt.getOrDefault(t, 0) + 1);
                }
            }
        }
        int res = 0;
        for (int v : cnt.values()) res = Math.max(res, v);
        return res == paths.length;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
