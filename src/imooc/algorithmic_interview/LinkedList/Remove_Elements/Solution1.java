package imooc.algorithmic_interview.LinkedList.Remove_Elements;

// 203. Remove Linked List Elements
// https://leetcode.com/problems/remove-linked-list-elements/description/
// 不使用虚拟头结点
// 时间复杂度: O(n)
// 空间复杂度: O(1)
public class Solution1 {

    public ListNode removeElements(ListNode head, int val) {

        // 需要对头结点进行特殊处理
        while (head != null && head.val == val) {//用while循环删除连续头节点都是要删除的节点
            ListNode node = head;
            head = node.next;
        }

        if (head == null)
            return head;

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
            } else
                cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 6, 3, 4, 5, 6};
        int val = 6;

        ListNode head = new ListNode(arr);
        System.out.println(head);


        System.out.println( (new Solution1()).removeElements(head, val));
    }
}
