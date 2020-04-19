package simple_fun.algorithm.DP.tree_dp;

/**
 * @Description: 树形动态规划的问题汇总
 * @Author: 67ng
 * @Date: 2020/4/11
 */
public class Solution {
    /**
     * @Name: 337.打家劫舍 III
     * @Description: 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
     * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
     * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额
     * @Linked: https://leetcode-cn.com/problems/house-robber-iii/
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //对于每个树根，有两种情况：被抢劫或未被抢劫。rob(root)不能区分这两种情况，因此“信息随着递归的深入而丢失”，从而导致重复出现子问题。
    public int rob(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];//res[0]表示未打劫，res[1]表示打劫

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }

}
