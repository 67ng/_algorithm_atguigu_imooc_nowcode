package algorithm.DP.knapsack;

/**
 * @Description: 有依赖的背包问题
 *
 * 有 N 个物品和一个容量是 V 的背包。
 *
 * 物品之间具有依赖关系，且依赖关系组成一棵树的形状。如果选择一个物品，则必须选择它的父节点。
 * 如下图所示：
 *        1
 *      /  \
 *     2    3
 *    / \
 *   4   5
 *
 * 如果选择物品5，则必须选择物品1和2。这是因为2是5的父节点，1是2的父节点。
 * 每件物品的编号是 i，体积是 vi，价值是 wi，依赖的父节点编号是 pi。物品的下标范围是 1…N。
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
 *
 * 输出最大价值。
 *
 * @Author: matreeix
 * @Date: 2020/8/21
 */

public class DependentSnapsack {
    public static int dependentSnapsack(){
        return 0;
    }

    public static void main(String[] args) {

    }


}
