package simple_fun.data_structure.tree.binary_tree.AVL;

/**
 * @Description: 判断一个二叉树是否是平衡二叉树
 * @Author: matreeix
 * @Date: 2020/3/24
 */
public class IsAVL {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    //求树的深度
    private int depth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
