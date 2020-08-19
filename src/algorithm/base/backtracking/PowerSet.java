package algorithm.base.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 幂集
 * * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 * * 说明：解集不能包含重复的子集。
 * <p>
 * 例如：输入： nums = [1,2,3]
 * 输出：
 * [
 *   [1],
 *   [2],
 *    [3],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/power-set-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: matreeix
 * @Date: 2020/8/18
 */

public class PowerSet {
    //回溯法,用位图法更加易懂，但这也是经典的回溯题
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        PowerSet s = new PowerSet();
        System.out.println(s.subsets2(arr));
    }
}
