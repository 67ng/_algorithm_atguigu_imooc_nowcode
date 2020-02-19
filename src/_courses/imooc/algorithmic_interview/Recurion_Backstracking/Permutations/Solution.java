package _courses.imooc.algorithmic_interview.Recurion_Backstracking.Permutations;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {

    private ArrayList<List<Integer>> res;
    private boolean[] used;//数组中数字是否被使用过

    public List<List<Integer>> permute(int[] nums) {

        res = new ArrayList<>();
        if(nums == null || nums.length == 0)
            return res;

        used = new boolean[nums.length];
        LinkedList<Integer> p = new LinkedList<>();
        generatePermutation(nums, 0, p);

        return res;
    }

    // p中保存了一个有index-1个元素的排列。
    // 向这个排列的末尾添加第index个元素, 获得一个有index个元素的排列
    private void generatePermutation(int[] nums, int index, LinkedList<Integer> p){

        if(index == nums.length){
            res.add((LinkedList<Integer>)p.clone());//使用克隆方法不然后续p里的元素会被删除
            return;
        }

        for(int i = 0 ; i < nums.length ; i ++)
            if(!used[i]){
                used[i] = true;
                p.addLast(nums[i]);
                generatePermutation(nums, index + 1, p );
                p.removeLast();//使用过的元素需要删除重复使用
                used[i] = false;
            }

        return;
    }

    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        List<List<Integer>> res = (new Solution()).permute(nums);
        for(List<Integer> list: res)
            printList(list);
    }
}
