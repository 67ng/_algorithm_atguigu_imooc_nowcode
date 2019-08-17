package imooc.algorithmic_interview.HashTable.others._242;

import java.util.Arrays;

/**
 * Description:使用hashmap
 *
 * @date: 2019/2/5 10:58
 */
public class Solution {
    public static boolean isAnagram(String s, String t) {
        if( s.length() != t.length() )
            return false;

        int[] freq = new int[26];
        for( int i = 0 ; i < s.length() ; i ++ )
            freq[s.charAt(i)-'a'] ++;

        for( int i = 0 ; i < t.length() ; i ++ ){
            freq[t.charAt(i)-'a'] --;
            if( freq[t.charAt(i)-'a'] < 0 )
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(Solution.isAnagram(s, t));
    }
}
