package data_structure.linear.linkedlist;


/**
 * @Description: 将二叉树扁平为链表
 * @Author: matreeix
 * @Date: 2020/6/24
 */

public class Tree2List {
    static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }

    //递归
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            flatten(root.left);
        if (root.right != null)
            flatten(root.right);

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null)
            root = root.right;
        root.right = tmp;
    }
}
