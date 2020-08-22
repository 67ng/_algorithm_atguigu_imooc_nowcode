package data_structure.tree.binary_tree.BIT;

import java.util.*;

/**
 * @Description: 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
 * @Author: matreeix
 * @Date: 2020/8/21
 */

public class ReversePairs {
    //树状数组
    class BIT {
        private int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        private int query(int x) {
            int ret = 0;
            while (x > 0) {
                ret += tree[x];
                x -= lowbit(x);
            }
            return ret;
        }

        private void update(int x) {
            while (x <= n) {
                ++tree[x];
                x += lowbit(x);
            }
        }
    }

    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) return 0;

        // 离散化：使得数字更紧凑，节约树状数组的空间
        // 1、使用二分搜索树是为了去掉重复元素
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            treeSet.add(nums[i]);
        }

        // 2、把排名存在哈希表里方便查询
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rankIndex = 1;
        for (Integer num : treeSet) {
            rankMap.put(num, rankIndex);
            rankIndex++;
        }

        int count = 0;
        // 在树状数组内部完成前缀和的计算
        // 规则是：从后向前，先给对应的排名 + 1，再查询前缀和
        BIT bit = new BIT(rankMap.size());
        for (int i = len - 1; i >= 0; i--) {
            int rank = rankMap.get(nums[i]);
            bit.update(rank);
            count += bit.query(rank - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4};
        ReversePairs rp = new ReversePairs();
        System.out.println(rp.reversePairs(arr));
    }
}
