package _courses.imooc.algorithmic_interview.LinkedList.Others._148;

/**
 * Description:Merge Sort Top Down
 * Time Complexity: O(nlogn)
 * Space Complexity: O(logn)
 *
 * @date: 2019/2/10 18:50
 */
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;
        head = sortList(head);
        head2 = sortList(head2);
        return merge(head, head2);
    }

    private ListNode merge(ListNode a, ListNode b) {

        ListNode dummyHead = new ListNode(-1);
        ListNode p1 = a, p2 = b, p = dummyHead;
        while (p1 != null && p2 != null)
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
                p.next = null;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
                p.next = null;
            }
        if (p1 != null) p.next = p1;
        if (p2 != null) p.next = p2;

        ListNode ret = dummyHead.next;
        return ret;
    }

    public static ListNode createLinkedList(int[] arr) {// 根据n个元素的数组arr创建一个链表, 并返回链表的头
        if (arr.length == 0)
            return null;

        ListNode head = new ListNode(arr[0]);
        ListNode curNode = head;
        for (int i = 1; i < arr.length; i++) {
            curNode.next = new ListNode(arr[i]);
            curNode = curNode.next;
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {// 打印以head为头结点的链表信息内容

        ListNode curNode = head;
        while (curNode != null) {
            System.out.print(curNode.val + " -> ");
            curNode = curNode.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 3, 3, 9, 6, 123, 31, 35, 2, 42, 35};
        printLinkedList(new Solution().sortList(createLinkedList(arr)));
    }

}
