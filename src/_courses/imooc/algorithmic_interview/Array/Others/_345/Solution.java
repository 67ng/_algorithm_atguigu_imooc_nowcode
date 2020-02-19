package _courses.imooc.algorithmic_interview.Array.Others._345;

/**
 * Description:
 *
 * @date: 2019/1/27 20:07
 */
public class Solution {
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int i = 0;
        int j = chs.length - 1;
        while (i <= j) {
            if (isVowel(Character.toLowerCase(chs[i])) && !isVowel(Character.toLowerCase(chs[j]))) {
                j--;
            } else if (!isVowel(Character.toLowerCase(chs[i])) && isVowel(Character.toLowerCase(chs[j]))) {
                i++;
            } else if (isVowel(Character.toLowerCase(chs[i])) && isVowel(Character.toLowerCase(chs[j]))) {
                swap(chs, i++, j--);
            } else {
                i++;
                j--;
            }
        }
        return new String(chs);
    }

    private void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
            return true;
        return false;
    }

    public static void main(String[] args) {
        String s = "Aa";
        Solution solution =new Solution();
        String s2 = solution.reverseVowels(s);
        System.out.println(s2);
    }
}
