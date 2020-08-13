package _cola.data_structure.tree.multiway_tree.segment_tree;

/**
 * Description:两区间融合方法的接口
 *
 * @date: 2018/11/27 21:48
 */
public interface Merger<E> {
    E merge(E a, E b);
}

