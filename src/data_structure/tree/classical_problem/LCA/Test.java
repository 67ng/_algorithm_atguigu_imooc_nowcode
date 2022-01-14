package data_structure.tree.classical_problem.LCA;

import java.util.*;

/**
 * @Description: 测试几种算法的差异
 * @Date: 2022/1/13
 */

public class Test {
    public static void main(String[] args) {
        int n = 100000;
        int m = 1000;
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 1; i <= n; i++) arr.push(i);
        Collections.shuffle(arr);
        TreeNode root = RMQ.createTree(arr);
        MultiplySolution multiplySolution = new MultiplySolution(root);

//        System.out.println("二叉树的层序遍历:" + RMQ.getLevelOrder(root));
        Random r = new Random();
        int cnt = 0;
        List<List<TreeNode>> query = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            TreeNode p = new TreeNode(r.nextInt(n)+1);
            TreeNode q = new TreeNode(r.nextInt(n)+1);
            List<TreeNode> list = new ArrayList<>();
            list.add(q);
            list.add(p);
            query.add(list);
        }

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < m; i++) {
            List<TreeNode> l = query.get(i);
            TreeNode p = l.get(0);
            TreeNode q = l.get(1);
            int res = multiplySolution.LCA(p.val, q.val);
        }
        long t2 = System.currentTimeMillis();

        for (int i = 0; i < m; i++) {
            List<TreeNode> l = query.get(i);
            TreeNode p = l.get(0);
            TreeNode q = l.get(1);
            int res = NaiveSolution.lowestCommonAncestor(root, p, q).val;
        }
        long t3 = System.currentTimeMillis();
        System.out.println("倍增算法耗时：" + (t2 - t1) + "ms");
        System.out.println("递归算法耗时：" + (t3 - t2) + "ms");

//        for (int i = 0; i < m; i++) {
//            TreeNode p = new TreeNode(r.nextInt(n)+1);
//            TreeNode q = new TreeNode(r.nextInt(n)+1);
////            int rmqRes = RMQ.lowestCommonAncestor(root, p, q).val;
//            int mulRes = multiplySolution.LCA(p.val, q.val);
//            int naiveRes = NaiveSolution.lowestCommonAncestor(root, p, q).val;
//
//            if (mulRes != naiveRes) {
//                System.out.println("Something Wrong!");
//                System.out.println("p:" + p.val);
//                System.out.println("q:" + q.val);
//                System.out.println("mulRes:" + mulRes);
//                System.out.println("naiveRes:" + naiveRes);
//            } else {
//                cnt++;
//            }
//        }
//        System.out.println("匹配总数：" + cnt);

    }
}
