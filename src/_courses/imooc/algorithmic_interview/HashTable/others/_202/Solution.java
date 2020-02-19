package _courses.imooc.algorithmic_interview.HashTable.others._202;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 *
 * @date: 2019/2/5 11:19
 */
public class Solution {
    public static boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        record.add(n);
        while (n != 1) {
            n = op(n);
            if (!record.contains(n))
                record.add(n);
            else
                return false;
        }
        return true;
    }

    private static int op(int x) {//每次平方和的结果
        int res = 0;
        while (x != 0) {
            int t = x % 10;
            res += t * t;
            x /= 10;
        }
//        System.out.println(res);
        return res;
    }


    public static void main(String[] args) {
        int n = 19;
        System.out.println(Solution.isHappy(n));
    }
}
