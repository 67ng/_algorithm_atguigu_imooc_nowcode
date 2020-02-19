package _courses.nowcode.High_Frequency_Questions._1;

/**
 * Description:1、已知一个字符串都是由左括号(和右括号)组成，判断该字符串是否是有效的括号组合。
 * 例子：
 * 有效的括号组合:()(),(()),(()())
 * 无效的括号组合:(,()),((),()(()
 * 2、题目进阶：
 * 已知一个字符串都是由左括号(和右括号)组成，返回最长有效括号子串的长度。
 *
 * @date: 2019/3/3 23:14
 */
public class ParenthesesProblem {
    public static boolean isValid(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        char[] chas = str.toCharArray();
        int status = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ')' && chas[i] != '(') {
                return false;
            }
            if (chas[i] == ')' && --status < 0) {
                return false;
            }
            if (chas[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int maxLength(String str) {//动态规划
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] dp = new int[chas.length];//以该索引位置元素 结尾 的最长有效子串长度
        int pre = 0;
        int res = 0;
        //（）(（）)
        // 01 2 34 5
        // 02 0 02 6
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {//若为'('直接为零
                pre = i - dp[i - 1] - 1;//dp[i - 1]起始位置前一个元素索引
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValid(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid(str3));
        System.out.println(maxLength(str3));

    }
}
