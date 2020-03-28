package simple_fun.data_structure.tree.binary_tree.BST.application;

/**
 * @Description: 给定链表，生成平衡BST
 * @Author: 67ng
 * @Date: 2020/3/27
 */
public class List2BST {

    //Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return convertBST(head, null);
    }

    //递归快慢指针法，秒啊
    public TreeNode convertBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) return null;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = convertBST(head, slow);
        root.right = convertBST(slow.next, tail);
        return root;
    }
}
