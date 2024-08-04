package data_structure.linear.ST;

/**
 * @Description: 线段树（Segment Tree）是一种用于解决区间查询和修改问题的数据结构，它可以将一个区间划分为多个子区间，并存储每个子区间的信息（如最小值、最大值、和等），以便快速回答查询和更新操作。
 * @Date: 2024/8/4
 */

public class SegmentTree {
    private int[] tree; // 线段树数组
    private int[] arr; // 原始数组
    private int n; // 数组长度

    public SegmentTree(int[] array) {
        n = array.length;
        arr = array;
        tree = new int[4 * n]; // 线段树数组的大小通常为原始数组大小的4倍
        buildTree(0, 0, n - 1); // 构建线段树
    }

    // 构建线段树，node为当前节点的索引，start和end分别为当前节点表示的区间的起始和结束位置
    private void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start]; // 叶子节点，存储原始数组的值
        } else {
            int mid = start + (end - start) / 2;
            buildTree(2 * node + 1, start, mid); // 递归构建左子树
            buildTree(2 * node + 2, mid + 1, end); // 递归构建右子树
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]); // 父节点存储子节点的最小值
        }
    }

    // 查询区间[l, r]的最小值
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    // 递归查询区间[l, r]的最小值，node为当前节点的索引，start和end分别为当前节点表示的区间的起始和结束位置
    private int query(int node, int start, int end, int l, int r) {
        if (r < start || l > end) {
            return Integer.MAX_VALUE; // 查询区间与当前节点表示的区间无交集
        }
        if (l <= start && r >= end) {
            return tree[node]; // 查询区间完全包含当前节点表示的区间
        }
        int mid = start + (end - start) / 2;
        int leftQuery = query(2 * node + 1, start, mid, l, r); // 递归查询左子树
        int rightQuery = query(2 * node + 2, mid + 1, end, l, r); // 递归查询右子树
        return Math.min(leftQuery, rightQuery); // 返回左右子树查询结果的较小值
    }

    // 更新原始数组arr[index]的值为val，并更新线段树
    public void update(int index, int val) {
        arr[index] = val; // 更新原始数组
        update(0, 0, n - 1, index, val); // 更新线段树
    }

    // 递归更新线段树，node为当前节点的索引，start和end分别为当前节点表示的区间的起始和结束位置
    private void update(int node, int start, int end, int index, int val) {
        if (start == end) {
            tree[node] = val; // 叶子节点，更新为新的值
        } else {
            int mid = start + (end - start) / 2;
            if (index <= mid) {
                update(2 * node + 1, start, mid, index, val); // 递归更新左子树
            } else {
                update(2 * node + 2, mid + 1, end, index, val); // 递归更新右子树
            }
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]); // 更新父节点
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println("The minimum element in range [1, 5] is " + segmentTree.query(1, 5)); // 输出应为0
        segmentTree.update(1, 10); // 将arr[1]更新为10
        System.out.println("The minimum element in range [1, 5] after update is " + segmentTree.query(1, 5)); // 输出应为2
    }
}
