package _courses.imooc.datastructure.HashTable;

/**
 * Description:leetcode387
 *
 * 寻找字符串中第一个只出现一次的字符，返回索引
 * @date: 2018/11/30 10:27
 */
class Solution {
    public int firstUniqChar(String s) {

        int[] freq = new int[26];
        for(int i = 0 ; i < s.length() ; i ++)
            freq[s.charAt(i) - 'a'] ++;

        for(int i = 0 ; i < s.length() ; i ++)
            if(freq[s.charAt(i) - 'a'] == 1)
                return i;

        return -1;
    }
}

