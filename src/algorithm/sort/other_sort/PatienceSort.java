package algorithm.sort.other_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 耐心排序
 * @Date: 2021/12/21
 */

public class PatienceSort {
    public static int[] patienceSort(int[] theArray) {
        List new_list = new ArrayList();
        for (int i = 0; i < theArray.length; i++) {
            List bucket_list = new ArrayList();
            if (i == 0) {
                bucket_list.add(theArray[i]);
                new_list.add(bucket_list);
            } else {
                boolean is_ok = false;
                for (int j = 0; j < new_list.size(); j++) {
                    if (theArray[i] < (int) ((List) new_list.get(j)).get(0)) {
                        ((List) new_list.get(j)).add(0, theArray[i]);
                        is_ok = true;
                        break;
                    }
                }
                if (!is_ok) {
                    bucket_list.add(theArray[i]);
                    new_list.add(bucket_list);
                }
            }
        }
        // 多维数组变成单维数组
        int[] ok_list = new int[theArray.length];
        int q = 0;
        for (int m = 0; m < new_list.size(); m++) {
            for (int n = 0; n < ((List) new_list.get(m)).size(); n++) {
                ok_list[q] = (int) ((List) new_list.get(m)).get(n);
                q++;
            }
        }

        //插入循环
        int n = ok_list.length;//将数组的长度赋给n是为了防止每次for循环中判断时都调用length方法影响性能
        int tmp;//用于中转数据
        int j;
        for (int i = 1; i < n; i++) {//排序的次数
            tmp = ok_list[i];
            for (j = i - 1; j >= 0 && ok_list[j] > tmp; j--) {//取i前面的所有跟i位置元素进行比较，先比较i-1和i，如果i-1大于i，则互换位置，i-1和i-2比较，以此类推
                ok_list[j + 1] = ok_list[j];
            }
            ok_list[j + 1] = tmp;
        }
        return ok_list;
    }

    public static void main(String[] args) {
        int[] the_array = {6, 4, 5, 1, 8, 7, 2, 3};
        System.out.print("之前的排序：");
        for (int i = 0; i < the_array.length; i++) {
            System.out.print(the_array[i] + " ");
        }

        int[] result_array = patienceSort(the_array);

        System.out.print("耐心排序：");
        for (int i = 0; i < result_array.length; i++) {
            System.out.print(result_array[i] + " ");
        }
    }

}
