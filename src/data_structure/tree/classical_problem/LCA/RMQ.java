package data_structure.tree.classical_problem.LCA;

import javafx.util.Pair;

import java.util.*;

/**
 * @Description: RMQ算法解决LCA
 * @Date: 2022/1/13
 */

public class RMQ {

    public static int N = 100000;
    public static int K = 20;
    public static int p_idx = -1, q_idx = -1;
    public static final ArrayList<Pair<TreeNode, Integer>> eulor_seq = new ArrayList<>();
    public static final HashMap<TreeNode, Integer> visited = new HashMap<>();
    public static int[][] F = new int[N][K];

    public static void dfs_get_eulor_seq(TreeNode root, TreeNode p, TreeNode q, int h) {
        eulor_seq.add(new Pair<>(root, h));
        visited.put(root, 1);
        if (root.val == p.val && p_idx == -1) p_idx = eulor_seq.size() - 1;
        if (root.val == q.val && q_idx == -1) q_idx = eulor_seq.size() - 1;
        if (root.left != null && visited.get(root.left) == null) {
            dfs_get_eulor_seq(root.left, p, q, h + 1);
            eulor_seq.add(new Pair<>(root, h));
        }
        if (root.right != null && visited.get(root.right) == null) {
            dfs_get_eulor_seq(root.right, p, q, h + 1);
            eulor_seq.add(new Pair<>(root, h));
        }
    }

    public static void ST_create() {
        int len = eulor_seq.size();
        int k = (int) (Math.log(len) / Math.log(2));
        for (int i = 0; i < len; ++i) F[i][0] = i;
        for (int j = 1; j <= k; ++j) {
            for (int i = 0; i + (1 << j) - 1 < len; ++i) {
                if (eulor_seq.get(F[i][j - 1]).getValue() <= eulor_seq.get(F[i + (1 << (j - 1))][j - 1]).getValue())
                    F[i][j] = F[i][j - 1];
                else
                    F[i][j] = F[i + (1 << (j - 1))][j - 1];
            }
        }
    }

    public static int RMQ_query(int l, int r) {
        int k = (int) (Math.log(r + 1 - l) / Math.log(2));
        if (eulor_seq.get(F[l][k]).getValue() <= eulor_seq.get(F[r - (1 << k) + 1][k]).getValue())
            return F[l][k];
        else
            return F[r - (1 << k) + 1][k];
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs_get_eulor_seq(root, p, q, 0);
        ST_create();
        if (p_idx > q_idx) {
            int tmp = p_idx;
            p_idx = q_idx;
            q_idx = tmp;
        }
        int idx = RMQ_query(p_idx, q_idx);
        return eulor_seq.get(idx).getKey();
    }


    public static TreeNode createTree(LinkedList<Integer> arr) {
        if (arr.size() == 0) return null;
        TreeNode root = new TreeNode(arr.pollFirst());
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!arr.isEmpty()) {
            List<TreeNode> tmpList = new ArrayList<>();
            for (TreeNode cur : list) {
                if (!arr.isEmpty()) cur.left = new TreeNode(arr.pollFirst());
                if (!arr.isEmpty()) cur.right = new TreeNode(arr.pollFirst());
                if (cur.left != null) tmpList.add(cur.left);
                if (cur.right != null) tmpList.add(cur.right);
            }
            list = tmpList;
        }
        return root;
    }

    public static List<List<Integer>> getLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }


    public static void main(String[] args) {
        RMQ rmq = new RMQ();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        System.out.println(lowestCommonAncestor(root,new TreeNode(6),new TreeNode(4)).val);

    }
}
