package algorithm.trick.moore_vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 摩尔投票算法
 * @Date: 2021/12/9
 */

public class MajorityElement {
    /**
     * 绝对众数:
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    // 时间复杂度O(N),空间复杂度O(1)
    public static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 求众数：
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/k ⌋ 次的元素。
     */
    // 时间复杂度O(N),空间复杂度O(k)
    public static List<Integer> majorityElementPro(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();//<candidate,vote>

        for (int num : nums) {
            if (!freqMap.containsKey(num) && freqMap.size() < k - 1) {// 记录k-1个候选人
                freqMap.put(num, 0);
            }
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.replaceAll((c, v) -> freqMap.get(c) - 1);// 所有候选人票数减一
                freqMap.values().removeIf(value -> value == 0);// 没有票数的候选人踢出去
            }
        }

        // 检查候选人是否符合条件.
        List<Integer> result = new ArrayList<>();
        freqMap.replaceAll((k1, v) -> 0);
        System.out.println(freqMap.keySet());
        for (int num : nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
                if (freqMap.get(num) == nums.length / k + 1)
                    result.add(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 2};
        System.out.println(majorityElementPro(nums, 2));
    }
}
