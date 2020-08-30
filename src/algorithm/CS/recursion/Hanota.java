package algorithm.CS.recursion;

import java.util.List;

/**
 * @Description: 汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * 你需要原地修改栈。
 * <p>
 * 链接：https://leetcode-cn.com/problems/hanota-lcci
 * @Author: matreeix
 * @Date: 2020/8/30
 */

public class Hanota {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if (A == null || B == null || C == null) return;

        move(A.size(), A, B, C);
    }

    public void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n < 1) return;

        move(n - 1, A, C, B);//先把上面 n - 1 个盘子从 A 移到 B
        C.add(A.remove(A.size() - 1));//将A中最大的盘子从移到 C
        move(n - 1, B, A, C);//再将 B 上 n - 1 个盘子从 B 移到 C
    }
}
