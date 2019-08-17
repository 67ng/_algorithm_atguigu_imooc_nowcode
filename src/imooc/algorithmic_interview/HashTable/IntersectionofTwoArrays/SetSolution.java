package imooc.algorithmic_interview.HashTable.IntersectionofTwoArrays;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Description:349. Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * @date: 2019/1/22 21:17
 */

// 时间复杂度: O(nlogn)
// 空间复杂度: O(n)
public class SetSolution {
    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> record = new TreeSet<Integer>();
//        HashSet<Integer> record = new HashSet<>();//使用哈希表会丧失数据的顺序性
        for(int num: nums1)
            record.add(num);

        TreeSet<Integer> resultSet = new TreeSet<Integer>();
        for(int num: nums2)
            if(record.contains(num))
                resultSet.add(num);

        int[] res = new int[resultSet.size()];
        int index = 0;
        for(Integer num: resultSet)
            res[index++] = num;

        return res;
    }

    private static void printArr(int[] arr){
        for(int e: arr)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = (new SetSolution()).intersection(nums1, nums2);
        printArr(res);
    }
}
