package imooc.algorithmic_interview.Array.Others._28;

/**
 * Description:
 *
 * @date: 2019/2/3 13:37
 */
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        if (needle.length() == 0) return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j ;
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
            if (j == needle.length()) return i;
        }
        return -1;

    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";

        String haystack2 = "aaaaa";
        String needle2 = "bba";

        String haystack3 = "aaabb";
        String needle3 = "baba";
        System.out.println(new Solution().strStr(haystack2, needle2));
    }
}
