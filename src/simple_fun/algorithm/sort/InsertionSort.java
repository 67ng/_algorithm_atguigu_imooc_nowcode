package simple_fun.algorithm.sort;

/**
 * @Description:插入排序
 * @Author: caffebaby
 * @Date: 2020/2/19
 */
public class InsertionSort {

    public int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    //优化
    public static int[] insertionSortOpt(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {

            // 寻找元素arr[i]合适的插入位置

            // 写法1
//            for( int j = i ; j > 0 ; j -- )
//                if( arr[j].compareTo( arr[j-1] ) < 0 )
//                    swap( arr, j , j-1 );
//                else
//                    break;

            // 写法2
//            for( int j = i; j > 0 && arr[j].compareTo(arr[j-1]) < 0 ; j--)
//                swap(arr, j, j-1);

            // 写法3
            int e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1] > e; j--)
                arr[j] = arr[j - 1];
            arr[j] = e;

        }
        return arr;
    }

}
