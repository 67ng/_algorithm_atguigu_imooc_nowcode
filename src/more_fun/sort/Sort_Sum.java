package more_fun.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort_Sum{

 //详细内容见下面网址
 //https://mp.weixin.qq.com/s?__biz=MzIwNTk5NjEzNw==&mid=2247486726&idx=1&sn=2ab01d70d3411b1db3f0cf0c7dbfab21&chksm=97292400a05ead16926aca0e192de97be69d0a36d2563306aefa7b27bd30d734303fba3c815a&mpshare=1&scene=1&srcid=12046A7QAEnrTOGTMjAG5RzB&pass_ticket=54dZ5vpvrHzMqPKaW8vTsH5eu%2FMXOBzFKuBs2oVBKPKFhhH9UhpdSYO2Wcs5Ul2c#rd


   //------------------------------------------------------------------------------------------


   //------------------------------------------------------------------------------------------


   /**
    * 归并排序
    *
    * @param array
    * @return
    */
   public static int[] MergeSort(int[] array) {
       if (array.length < 2) return array;
       int mid = array.length / 2;
       int[] left = Arrays.copyOfRange(array, 0, mid);
       int[] right = Arrays.copyOfRange(array, mid, array.length);
       return merge(MergeSort(left), MergeSort(right));
   }
   /**
    * 归并排序——将两段排序好的数组结合成一个排序数组
    *
    * @param left
    * @param right
    * @return
    */
   public static int[] merge(int[] left, int[] right) {
       int[] result = new int[left.length + right.length];
       for (int index = 0, i = 0, j = 0; index < result.length; index++) {
           if (i >= left.length)
               result[index] = right[j++];
           else if (j >= right.length)
               result[index] = left[i++];
           else if (left[i] > right[j])
               result[index] = right[j++];
           else
               result[index] = left[i++];
       }
       return result;
   }
   //------------------------------------------------------------------------------------------

   /**
    * 快速排序方法
    * @param array
    * @param start
    * @param end
    * @return
    */
   public static int[] QuickSort(int[] array, int start, int end) {
       if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
       int smallIndex = partition(array, start, end);
       if (smallIndex > start)
           QuickSort(array, start, smallIndex - 1);
       if (smallIndex < end)
           QuickSort(array, smallIndex + 1, end);
       return array;
   }
   /**
    * 快速排序算法——partition
    * @param array
    * @param start
    * @param end
    * @return
    */
   public static int partition(int[] array, int start, int end) {
       int pivot = (int) (start + Math.random() * (end - start + 1));
       int smallIndex = start - 1;
       swap(array, pivot, end);
       for (int i = start; i <= end; i++)
           if (array[i] <= array[end]) {
               smallIndex++;
               if (i > smallIndex)
                   swap(array, i, smallIndex);
           }
       return smallIndex;
   }

   /**
    * 交换数组内两个元素
    * @param array
    * @param i
    * @param j
    */
   public static void swap(int[] array, int i, int j) {
       int temp = array[i];
       array[i] = array[j];
       array[j] = temp;
   }
   //--------------------------------------------------------------------------------------------

   //声明全局变量，用于记录数组array的长度；


   //------------------------------------------------------------------------------------------

   /**
    * 计数排序
    *
    * @param array
    * @return
    */

   //------------------------------------------------------------------------------------------

   /**
    * 桶排序
    *
    * @param array
    * @param bucketSize
    * @return
    */
   public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
       if (array == null || array.size() < 2)
           return array;
       int max = array.get(0), min = array.get(0);
       // 找到最大值最小值
       for (int i = 0; i < array.size(); i++) {
           if (array.get(i) > max)
               max = array.get(i);
           if (array.get(i) < min)
               min = array.get(i);
       }
       int bucketCount = (max - min) / bucketSize + 1;
       ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
       ArrayList<Integer> resultArr = new ArrayList<>();
       for (int i = 0; i < bucketCount; i++) {
           bucketArr.add(new ArrayList<Integer>());
       }
       for (int i = 0; i < array.size(); i++) {
           bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
       }
       for (int i = 0; i < bucketCount; i++) {
           if (bucketCount == 1)
               bucketSize--;
           ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
           for (int j = 0; j < temp.size(); j++)
               resultArr.add(temp.get(j));
       }
       return resultArr;
   }
   //------------------------------------------------------------------------------------------

   /**
    * 基数排序
    * @param array
    * @return
    */

}

