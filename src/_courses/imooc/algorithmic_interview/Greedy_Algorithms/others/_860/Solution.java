package _courses.imooc.algorithmic_interview.Greedy_Algorithms.others._860;

/**
 * Description:
 *
 * @date: 2019/2/3 12:13
 */
public class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] res = new int[3];//剩余钱5，10，20的数目
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                res[0]++;
            } else if (bills[i] == 10) {
                if (res[0] == 0) return false;
                res[0]--;
                res[1]++;
            } else {
                res[2]++;
                if (res[1] == 0) {
                    if (res[0] < 3) return false;
                    res[0] = res[0] - 3;
                } else {//贪心策略:尽量把5的钱留下
                    if (res[0] == 0) return false;
                    res[0]--;
                    res[1]--;
                }
            }
        }
        return true;

    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        int[] bills2 = {5, 5, 10, 10, 20};
        System.out.println(new Solution().lemonadeChange(bills2));
    }
}
