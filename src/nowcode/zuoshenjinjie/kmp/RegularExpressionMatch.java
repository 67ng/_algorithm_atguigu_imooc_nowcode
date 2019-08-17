package nowcode.zuoshenjinjie.kmp;

/**
 * Description:字KM符P算串法匹扩配展问题题目
 * <p>
 * 【题目】
 * 给定字符串str，其中绝对不含有字符'.'和'*'。再给定字符串exp，
 * 其中可以含有'.'或'*'，'*'字符不能是exp的首字符，并且任意两个
 * '*'字符不相邻。exp中的'.'代表任何一个字符，exp中的'*'表示'*'
 * 的前一个字符可以有0个或者多个。请写一个函数，判断str是否能被
 * exp匹配。
 * 【举例】
 * str="abc"，exp="abc"，返回true。
 * str="abc"，exp="a.c"，exp中单个'.'可以代表任意字符，所以返回
 * true。
 * str="abcd"，exp=".*"。exp中'*'的前一个字符是'.'，所以可表示任
 * 意数量的'.'字符，当exp是"...."时与"abcd"匹配，返回true。
 * str=""，exp="..*"。exp中'*'的前一个字符是'.'，可表示任意数量
 * 的'.'字符，但是".*"之前还有一个'.'字符，该字符不受'*'的影响，
 * 所以str起码有一个字符才能被exp匹配。所以返回false。
 *
 * @author: NULL
 * @date: 2018/11/2 15:28
 */
public class RegularExpressionMatch {
    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) ? process(s, e, 0, 0) : false;
    }


    //暴力递归
    public static boolean process(char[] str, char[] exp, int i, int j) {//str和exp分别从i，j开始到最后能否匹配
        if (j == exp.length) {//j到达边界时i也必须到达边界才返回true
            return i == str.length;
        }
        if (j + 1 == exp.length || exp[j + 1] != '*') {//（j + 1）是边界(说明j有东西)或者有东西但不是'*'
            return i != str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);//只有i不是边界且能匹配j位置且后面的递推能匹配才返回true
        }
        //j+1位置为'*'
        while (i != str.length && (exp[j] == str[i] || exp[j] == '.')) {//i和j能匹配
            if (process(str, exp, i, j + 2)) {//让exp从j+2位置开始去和str从i开始每一个后缀匹配尝试，成功就返回true
                return true;
            }
            i++;//每次str字符串向后蹿一个位置
        }
        return process(str, exp, i, j + 2);//i和j不匹配，且j+1为'*'，默认j和j+1位置合起来为空直接跳到j+2位置去和i比较
    }

    //动态规划
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));

    }

}
