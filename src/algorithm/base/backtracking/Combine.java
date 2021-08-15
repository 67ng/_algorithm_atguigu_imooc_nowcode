package algorithm.base.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * @Date: 2021/7/31
 */

public class Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        dfs(res, tmp, n, k, 1);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> tmp, int n, int k, int begin) {
        if (k <= 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = begin; i <= n - k + 1; i++) { // make sure there are at least (k-1) nums after this
            tmp.add(i);
            dfs(res, tmp, n, k - 1, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
