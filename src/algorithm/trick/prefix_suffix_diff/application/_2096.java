package algorithm.trick.prefix_suffix_diff.application;

/**
 * @Description: 2096. 从二叉树一个节点到另一个节点每一步的方向
 * 给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。给你一个整数 startValue ，
 * 表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。
 * 请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：
 * 'L' 表示从一个节点前往它的 左孩子 节点。
 * 'R' 表示从一个节点前往它的 右孩子 节点。
 * 'U' 表示从一个节点前往它的 父 节点。
 * 请你返回从 s 到 t 最短路径 每一步的方向。
 * 提示：
 * 树中节点数目为 n 。
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * 树中所有节点的值 互不相同。
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 * 链接：https://leetcode-cn.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another
 * @Date: 2022/1/12
 */

public class _2096 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public String tmp;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        getPath(root, startValue, new StringBuilder());
        String start = tmp;
        getPath(root, destValue, new StringBuilder());
        String end = tmp;
        int len = Math.min(start.length(), end.length());
        int i = 0;
        while (i < len && start.charAt(i) == end.charAt(i)) i++;
        int up = start.substring(i).length();
        end = end.substring(i);
        StringBuilder ans = new StringBuilder();
        while (up > 0) {
            ans.append("U");
            up--;
        }
        return ans.append(end).toString();
    }

    private void getPath(TreeNode root, int target, StringBuilder sb) {
        if (root == null) return;
        if (root.val == target) {
            tmp = sb.toString();
            return;
        }
        getPath(root.left, target, sb.append("L"));
        sb.deleteCharAt(sb.length() - 1);
        getPath(root.right, target, sb.append("R"));
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     * 0
     * /   \
     * 1     2
     * / \   / \
     * 3   4 5   6
     * /     /
     * 8     7
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
//        getPath(root, 2, new StringBuilder());
//        System.out.println(getDirections(root,8,7));
    }
}
