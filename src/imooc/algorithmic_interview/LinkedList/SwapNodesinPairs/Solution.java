package imooc.algorithmic_interview.LinkedList.SwapNodesinPairs;

// 24. Swap Nodes in Pairs
// https://leetcode.com/problems/swap-nodes-in-pairs/description/
// 时间复杂度: O(n)
// 空间复杂度: O(1)
public class Solution {

    public ListNode swapPairs(ListNode head) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = dummyHead;
        while(p.next != null && p.next.next != null ){
            //存储节点
            ListNode node1 = p.next;//node1和node2是要交换的俩节点
            ListNode node2 = node1.next;
            ListNode next = node2.next;
            //进行交换
            node2.next = node1;
            node1.next = next;
            p.next = node2;

            p = node1;//跳到下一对要交换的节点
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};

        ListNode head = new ListNode(arr);
        System.out.println(head);

        head = (new Solution()).swapPairs(head);
        System.out.println(head);
    }
}
