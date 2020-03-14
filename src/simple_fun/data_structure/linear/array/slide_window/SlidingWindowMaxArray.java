package simple_fun.data_structure.linear.array.slide_window;

import java.util.LinkedList;

import static _courses.nowcode.zuoshenjinjie.bfprt.BFPRT.printArray;

/**
 * Description:生成窗口(范围为三)中的最大值数组
 * 窗口每次向右移动一个单位
 *注意:窗口左右边界只能向右移动，且左小于右
 * @author: NULL
 * @date: 2018/10/26 19:56
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();//双向链表
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();//若新添加的数不小于链表末尾的数，一直弹出直到某个数大于它，就添加其索引值到后面
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();//若链表头的数在前面没被弹出，当小于左边界(i - w)时就弹出
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];//当索引大于窗口长度时，索引每次增加就依次选出表头的数(即最大数)添加到数组中
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {4,3,5,4,3,3,6,7};
        printArray(getMaxWindow(test, 3));
    }

}
