package test;

import java.util.Arrays;

/**
 * Description:测试subString()方法和tostring方法
 * <p>
 * tostring方法只是展现数组，带有格式，不能变成字符串
 *
 * @author: NULL
 * @date: 2018/11/8 22:16
 */
public class Test3 {
    public static void main(String[] args) {
        String str = "qwertyuiop";
        System.out.println(str.substring(7));
        int[] arr2 = {1, 2, 3, 7, 8, 5, 9, 4, 0, 6};
        System.out.println(Arrays.toString(arr2));
        StringBuilder sb = new StringBuilder();
        for (int n : arr2) {
            sb.append(n);
        }
        System.out.println(sb.toString());
    }
}
