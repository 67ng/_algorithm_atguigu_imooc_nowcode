package _courses.imooc.algorithmic_interview.Array.Others._125;

/**
 * Description:
 *
 * @date: 2019/1/27 18:08
 */
public class Solution {
    public boolean isPalindrome(String s) {
        int i = nextAlphaNumeric(s, 0);
        int j = prevAlphaNumeric(s, s.length() - 1);
        char[] chs = s.toLowerCase().toCharArray();
        while (i <= j) {
            if (chs[i] != chs[j])
                return false;
            i = nextAlphaNumeric(s, i + 1);
            j = prevAlphaNumeric(s, j - 1);
        }
        return true;
    }

    private int nextAlphaNumeric(String s, int index) {
        char[] chs = s.toCharArray();
        for (int i = index; i < s.length(); i++)
            if (Character.isLetterOrDigit(chs[i]))
                return i;
        return s.length();
    }

    private int prevAlphaNumeric(String s, int index) {
        char[] chs = s.toCharArray();
        for (int i = index; i >= 0; i--)
            if (Character.isLetterOrDigit(chs[i]))
                return i;
        return -1;
    }

    public static void main(String[] args) {
//        String s ="A man, a plan, a canal: Panama";
        String s = "race a car";
        System.out.println(new Solution().isPalindrome(s));
    }
}
