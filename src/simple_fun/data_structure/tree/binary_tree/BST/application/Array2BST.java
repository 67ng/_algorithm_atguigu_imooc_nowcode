package simple_fun.data_structure.tree.binary_tree.BST.application;

/**
 * @Description: 给定数组，生成一颗平衡BST
 * @Author: 67ng
 * @Date: 2020/3/27
 */
public class Array2BST {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0)
            return null;
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    //每次选择中间数生成根节点
    public TreeNode helper(int[] num, int low, int high) {
        if (low > high)
            return null;

        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }


    //Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
