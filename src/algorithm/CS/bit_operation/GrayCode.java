package algorithm.CS.bit_operation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 格雷编码
 * <p>
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 * <p>
 * 链接：https://leetcode-cn.com/problems/gray-code
 * @Author: matreeix
 * @Date: 2020/8/29
 */

public class GrayCode {

    /**
     * 镜像反射法
     * 思路：
     * 设 n 阶格雷码集合为 G(n)，则 G(n+1) 阶格雷码为：
     * 给 G(n) 阶格雷码每个元素二进制形式前面添加 0，得到 G'(n)；
     * 设 G(n) 集合倒序（镜像）为 R(n)，给 R(n) 每个元素二进制形式前面添加 1，得到 R'(n)；
     * G(n+1) = G'(n) ∪ R'(n) 拼接两个集合即可得到下一阶格雷码。
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>() {{
            add(0);
        }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            head <<= 1;
        }
        return res;
    }

    //数学公式法
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        int store = (1 << n);//有2^n个结果
        for (int i = 0; i < store; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }

}
