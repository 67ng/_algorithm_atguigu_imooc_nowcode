package _cola.data_structure.tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description:
 * @Author: matreeix
 * @Date: 2020/3/27
 */
public class LevelTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    private List<List<Integer>> levels = new ArrayList<>();

    public void helper(Node node, int level) {
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        levels.get(level).add(node.value);

        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    //递归
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return
                levels;
        helper(root, 0);
        return levels;
    }


    //非递归1
    public static void levelTraversal(Node head) {
        if (head == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (queue.size() != 0) {
            head = queue.poll();
            System.out.print(head.value + " ");

            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
        }
    }

    //非递归2
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                Node node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.value);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
//        head.left.left = new Node(4);
        head.left.right = new Node(5);
//        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversal(head);
    }
}
