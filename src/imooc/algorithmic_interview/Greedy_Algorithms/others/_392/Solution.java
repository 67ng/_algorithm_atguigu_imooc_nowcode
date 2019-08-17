package imooc.algorithmic_interview.Greedy_Algorithms.others._392;

/**
 * Description:
 *
 * @date: 2019/2/3 10:36
 */
public class Solution {
    public boolean isSubsequence(String s, String t) {

        if (s.length() == 0) return true;

        int i = 0;
        int j = 0;
        while (j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if(i ==s.length())return true;
            }
            j++;

        }
        return false;
    }

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(new Solution().isSubsequence(s, t));
    }
}
