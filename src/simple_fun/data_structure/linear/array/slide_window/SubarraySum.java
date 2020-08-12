package simple_fun.data_structure.linear.array.slide_window;

import java.util.HashMap;

/**
 * @Description: 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * @Author: matreeix
 * @Date: 2020/5/22
 */
public class SubarraySum {
    //前缀和+哈希表优化，时间复杂度和空间复杂度都是O(n)
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;//pre[i]为[0..i]里所有数的和
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k))//pre[j]=pre[i]-k,代表存在[j,i]的子数组和为k
                count += mp.get(pre - k);
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
