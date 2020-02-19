package _courses.nowcode.zuoshen.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description:判断一棵树是否是搜索二叉树(中序遍历的值是升序的)
 * 判断一棵树是否是完全二叉树(按层遍历时:1.有右子树无左子树返回false;2.有左子树或无子树时，遍历其后面的所有
 * 节点都是叶子结点返回true，否则返回false)
 *
 * @author: 黑山老妖
 * @date: 2018/10/16 23:11
 */
public class IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //  morris方法判定二叉搜索树
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    //  根据中序遍历定义来判定
    public static boolean inOrderUnRecurToBST(Node head) {
        int pre = 0;
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (head.value < pre) {//后继节点小就返回false
                        return false;
                    } else {
                        pre = head.value;
                    }
                    head = head.right;
                }
            }

        }
        return true;
    }

    //    完全二叉树的判定:按层遍历
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
        System.out.println(inOrderUnRecurToBST(head));
        System.out.println(isCBT(head));

    }

}
