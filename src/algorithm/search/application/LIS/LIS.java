package algorithm.search.application.LIS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Name: 300.最长上升子序列
 * @Description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * @Linked: https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
 */

public class LIS {
    // 模板1
    public static int LISDemo1(int[] nums) {
        int[] tails = new int[nums.length];// tails[i]:所有长度为i+1的递增子序列中, 最小的那个序列尾数.
        int size = 0;
        for (int num : nums) {
            int i = 0, j = size;
            while (i != j) {
                int mid = (i + j) / 2;
                if (tails[mid] < num)
                    i = mid + 1;
                else
                    j = mid;
            }
            tails[i] = num;//if tails[i-1] < num <= tails[i], update tails[i]
            if (i == size) ++size;// if num is larger than all tails, append it, increase the size by 1
        }
        return size;
    }

    // 模板2，耐心排序
    public static int LISDemo2(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile;
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
            }
        }
        return piles.size();
    }

    // 得到LIS的路径
    public List<Integer> lengthOfLIS(int[] nums) {
        List<Node> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            Node node = new Node(num);
            int pile = Collections.binarySearch(piles, node);
            if (pile < 0) pile = ~pile;

            if (pile != 0) {
                node.prev = piles.get(pile - 1);
            }

            if (pile == piles.size()) {
                piles.add(node);
            } else {
                piles.set(pile, node);
            }
        }
        return extractLIS(piles);
    }

    private List<Integer> extractLIS(List<Node> piles) {
        List<Integer> result = new ArrayList<>(piles.size());
        for (Node curr = piles.isEmpty() ? null : piles.get(piles.size() - 1); curr != null; curr = curr.prev) {
            result.add(curr.val);
        }
        Collections.reverse(result);
        return result;
    }

    private static class Node implements Comparable<Node> {
        int val;
        Node prev;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.val, that.val);
        }
    }

}
