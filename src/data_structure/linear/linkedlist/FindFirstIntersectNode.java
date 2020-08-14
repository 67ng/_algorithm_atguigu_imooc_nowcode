package data_structure.linear.linkedlist;

/**
 * Description:两个单链表相交的一系列问题
 *
 * @author: matreeix
 * @date: 2018/10/16 14:41
 */
       /*【题目】 在本题中，单链表可能有环，也可能无环。给定两个
        单链表的头节点 head1和head2，这两个链表可能相交，也可能
        不相交。请实现一个函数， 如果两个链表相交，请返回相交的
        第一个节点；如果不相交，返回null 即可。 要求：如果链表1
        的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外
        空间复杂度请达到O(1)。*/
public class FindFirstIntersectNode {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){this.value = data;}
    }

    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null){return null;}
//        得到有环链表的入环节点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
//        两个都无环
        if (loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
//        两个都有环
        if (loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
//        一个有环一个无环必不相交
        return null;
    }

    public static Node getLoopNode(Node head){
//        形成环至少有三个节点
        if (head == null || head.next == null || head.next.next == null){return null;}
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2){
//            无环的情况返回null
            if (n2.next == null || n2.next.next == null){return null;}
            n2 = n2.next.next;
            n1 = n1.next;
        }
//        当n2追上n1时，把head赋给n2，n1和n2再次相遇必在环节点处
        n2 = head;
        while (n1 != n2){
            n2 = n2.next;
            n1 = n1.next;
        }
        return n1;
    }

    public static Node noLoop(Node head1, Node head2){
//        if (head1 == null || head2 == null) {
//			return null;
//		}
        Node cur1 = head1;
        Node cur2 = head2;
        int n =0;
        while (cur1.next == null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next == null){
            n--;
            cur2 = cur2.next;
        }
//        两链表不相交
        if (cur1 != cur2){return null;}
//        cur1赋值为长链表头，cur2赋值为短链表头
        cur1 = n >= 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
//        让n1先把多的长度走完
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
//        得到交节点
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
//        两个环链表的环节点一样
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n =0;
            while (cur1.next == null){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next == null){
                n--;
                cur2 = cur2.next;
            }
//        两链表不相交
            if (cur1 != cur2){return null;}
//        cur1赋值为长链表头，cur2赋值为短链表头
            cur1 = n >= 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
//        让n1先把多的长度走完
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
//        得到交节点
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){return loop1;}//环节点不一样但环相同
                cur1 = cur1.next;
            }
            return null;//不想交
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
