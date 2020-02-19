package _courses.imooc.algorithmic_interview.BinaryTree_Recursion.PathSum;

/// 112. Path Sum
/// https://leetcode.com/problems/path-sum/description/
/// 时间复杂度: O(n), n为树的节点个数
/// 空间复杂度: O(h), h为树的高度
class Solution {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null)
            return false;

        if(root.left == null && root.right == null)//判断该节点是叶子节点不是跟节点
            return sum == root.val;//递归终止条件

        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}