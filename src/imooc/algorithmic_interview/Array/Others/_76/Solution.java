package imooc.algorithmic_interview.Array.Others._76;

/**
 * Description:
 *
 * @date: 2019/1/27 17:17
 */
public class Solution {
    public String minWindow(String s, String t) {
        char[] cht = t.toLowerCase().toCharArray();
        int[] tFreq = new int[256];
        for (int i = 0; i < cht.length; i++)
            tFreq[cht[i] - 'a']++;

        char[] chs = s.toLowerCase().toCharArray();
        int[] sFreq = new int[256];
        int sCnt = 0;
        int minLength = chs.length + 1;
        int startIndex = -1;

        int l = 0, r = -1;
        while (l < chs.length) {
            if (r + 1 < chs.length && sCnt < cht.length) {
                sFreq[chs[r + 1] - 'a']++;
                if (sFreq[chs[r + 1] - 'a'] <= tFreq[chs[r + 1] - 'a'])
                    sCnt++;
                r++;
            } else {
                assert (sCnt <= cht.length);
                if (sCnt == cht.length && r - l + 1 < minLength) {
                    minLength = r - l + 1;
                    startIndex = l;
                }
                sFreq[chs[l]]--;
                if (sFreq[chs[l] - 'a'] < tFreq[chs[l] - 'a'])
                    sCnt--;
                l++;
            }
        }

        if (startIndex != -1)
            return s.substring(startIndex, startIndex + minLength);

        return "";
    }

    public static void main(String[] args) {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        System.out.println(new Solution().minWindow(S, T));
    }
}
