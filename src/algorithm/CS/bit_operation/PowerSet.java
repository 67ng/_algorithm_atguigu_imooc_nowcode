package algorithm.CS.bit_operation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 幂集
 *  * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 *  * 说明：解集不能包含重复的子集。
 *
 *  例如：输入： nums = [1,2,3]
 *  输出：
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/power-set-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: matreeix
 * @Date: 2020/8/18
 */

public class PowerSet {
    /**
     * 原理：例如 [1, 2, 3] 有三位可以从 0 遍历到 7 也就是 2 ^ 3 - 1 用二进制表示就是 000, 001, 010, 011, 100, 101, 110, 111 正好代表了全部子集。
     */
    //位运算,妙啊！
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int bmp = 1 << nums.length;
        for (int i = 0; i < bmp; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++)
                if ((i >> j & 1) == 1)//判断每一位是否为1
                    subset.add(nums[j]);
            subsets.add(subset);
        }
        return subsets;
    }
}
