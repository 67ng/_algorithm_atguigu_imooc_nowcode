package _courses.imooc.algorithmic_interview.LinkedList.Remove_Elements;

// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/description/
// 使用虚拟头结点
// 时间复杂度: O(n)
// 空间复杂度: O(1)
public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

        // 创建虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
            } else
                cur = cur.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] arr = {6, 6, 1, 6, 2, 6, 3, 4, 5, 6};
        int val = 6;

        ListNode head = new ListNode(arr);
        System.out.println(head);

        System.out.println((new Solution2()).removeElements(head, val));
    }
}
