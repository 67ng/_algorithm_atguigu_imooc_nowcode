package _courses.imooc.datastructure.segment_tree;

/**
 * Description:应用线段树解决
 *
 * @date: 2018/11/27 22:15
 */
public class Solution1 {
    /// 303. Range Sum Query - Immutable
/// https://leetcode.com/problems/range-sum-query-immutable/description/

    class NumArray {

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {

            if (nums.length > 0) {
                Integer[] data = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++)
                    data[i] = nums[i];
                segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
            }

        }

        public int sumRange(int i, int j) {

            if (segmentTree == null)
                throw new IllegalArgumentException("Segment Tree is null");

            return segmentTree.query(i, j);
        }
    }
}
