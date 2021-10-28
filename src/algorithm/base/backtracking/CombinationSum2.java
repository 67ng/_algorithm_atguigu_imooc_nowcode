package algorithm.base.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * @Date: 2021/10/28
 */

public class CombinationSum2 {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(new ArrayList<>(), 0, 0, candidates, target);
        return res;
    }

    private void backtrack(List<Integer> tmp, int cur, int index, int[] candidates, int target){
        if(cur > target) return;
        if(cur == target){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(i > index && candidates[i] == candidates[i - 1])
                continue;
            tmp.add(candidates[i]);
            backtrack(tmp, cur + candidates[i], i + 1, candidates, target);
            tmp.remove(tmp.size() - 1);
        }
    }
}
