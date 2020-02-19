package _courses.nowcode.zuoshen.sort;

import java.util.Arrays;

import static _courses.nowcode.zuoshen.sort.InsertionSort.swap;

/**
 * Description:荷兰国旗问题
 *
 * @author: 黑山老妖
 * @date: 2018/10/13 16:56
 */
        /*给定一个数组arr，和一个数num，请把小于num的数放在数组的
        左边，等于num的数放在数组的中间，大于num的数放在数组的
        右边。要求额外空间复杂度O(1)，时间复杂度O(N)*/
public class NetherlandsFlag {
    public static int[] partition(int[] arr, int l, int r, int p ){
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more){
            if (arr[cur] < p){
                swap(arr, ++less, cur++);//cur从一开始，less域扩大，cur要跳出less域所以要加一
            }else if (arr[cur] > p){
                swap(arr, --more, cur);//more在右边，只需扩大more域
            }else {
                //arr[cur] == p
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void main(String[] args) {
        int[] ints = {1,4,2,34,4,6,5,1223,4,1,44,2,2,0,8,80,5,3,9,};
        System.out.println(Arrays.toString(partition(ints,0, 18,4)));
    }
}
