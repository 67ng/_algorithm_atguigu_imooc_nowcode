package data_structure.linear.queue;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * /// Leetcode 102. Binary Tree Level Order Traversal
 * /// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 * /// 二叉树的层序遍历
 * ///
 * /// 二叉树的层序遍历是一个典型的可以借助队列解决的问题。
 * /// 该代码主要用于使用Leetcode上的问题测试我们的ArrayQueue。
 * /// 对于二叉树的层序遍历，这个课程后续会讲到。
 * /// 届时，同学们也可以再回头看这个代码。
 * /// 不过到时，大家应该已经学会自己编写二叉树的层序遍历啦：）
 *
 * @author: matreeix
 * @date: 2018/11/25 11:52
 */
public class Solution {
    /// Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;

        // 我们使用LinkedList来做为我们的先入先出的队列
        ArrayQueue<Pair<TreeNode, Integer>> queue = new ArrayQueue<Pair<TreeNode, Integer>>();
        //LoopQueue<Pair<TreeNode, Integer>> queue = new LoopQueue<Pair<TreeNode, Integer>>();
        queue.enqueue(new Pair<TreeNode, Integer>(root, 0));

        while (!queue.isEmpty()) {

            Pair<TreeNode, Integer> front = queue.dequeue();
            TreeNode node = front.getKey();
            int level = front.getValue();

            if (level == res.size())
                res.add(new ArrayList<Integer>());
            assert level < res.size();

            res.get(level).add(node.val);
            if (node.left != null)
                queue.enqueue(new Pair<TreeNode, Integer>(node.left, level + 1));
            if (node.right != null)
                queue.enqueue(new Pair<TreeNode, Integer>(node.right, level + 1));
        }
        return res;
    }
}
