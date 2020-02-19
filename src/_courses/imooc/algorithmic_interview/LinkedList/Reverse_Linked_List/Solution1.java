package _courses.imooc.algorithmic_interview.LinkedList.Reverse_Linked_List;

// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/description/
// 时间复杂度: O(n)
// 空间复杂度: O(1)
public class Solution1 {

    // Definition for singly-linked list.
  /*  public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }*/

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;//反转链表
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode head2 = (new Solution1()).reverseList(head);
        System.out.println(head2);
    }
}
