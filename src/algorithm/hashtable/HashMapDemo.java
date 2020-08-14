package algorithm.hashtable;

import java.util.*;
import java.util.Map.Entry;

/**
 * Description:认识哈希函数和哈希表
 *
 * @author: matreeix
 * @date: 2018/10/17 22:48
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("zuo", "31");

        System.out.println(map.containsKey("zuo"));//true
        System.out.println(map.containsKey("chengyun"));//false
        System.out.println("=========================");

        System.out.println(map.get("zuo"));//31
        System.out.println(map.get("chengyun"));//null
        System.out.println("=========================");

        System.out.println(map.isEmpty());//false
        System.out.println(map.size());//1
        System.out.println("=========================");

        System.out.println(map.remove("zuo"));//31(删除并返回value)
        System.out.println(map.containsKey("zuo"));//false
        System.out.println(map.get("zuo"));//null
        System.out.println(map.isEmpty());//true
        System.out.println(map.size());//0
        System.out.println("=========================");

        map.put("zuo", "31");
        System.out.println(map.get("zuo"));//31
        map.put("zuo", "32");//覆盖前一次的value值
        System.out.println(map.get("zuo"));//32
        System.out.println("=========================");

        map.put("zuo", "31");
        map.put("cheng", "32");
        map.put("yun", "33");

        for (String key : map.keySet()) {//返回此地图中包含的键的Set视图Set<K>
            System.out.println(key);//yun zuo cheng
        }
        System.out.println("=========================");

        for (String values : map.values()) {//返回此地图中包含的值的Collection视图
            System.out.println(values);//33 31 32
        }
        System.out.println("=========================");

        map.clear();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "1");
        map.put("E", "2");
        map.put("F", "3");
        map.put("G", "1");
        map.put("H", "2");
        map.put("I", "3");
        for (Entry<String, String> entry : map.entrySet()) {//返回此地图中包含的映射的Set视图Set<Map.Entry<K,V>>
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "," + value);
        }
        System.out.println("=========================");

//         you can not remove item in map when you use the iterator of map
//		 for(Entry<String,String> entry : map.entrySet()){
//			 if(!entry.getValue().equals("1")){
//				 map.remove(entry.getKey());
//			 }
//		 }

        // if you want to remove items, collect them first, then remove them by
        // this way.
        List<String> removeKeys = new ArrayList<String>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("1")) {
                removeKeys.add(entry.getKey());
            }
        }
        for (String removeKey : removeKeys) {
            map.remove(removeKey);
        }
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "," + value);
        }
        System.out.println("=========================");

        /**HashMap 类映射不保证顺序；某些映射可明确保证其顺序:TreeMap类

         *在遍历Map过程中,不能用map.put(key,newVal),map.remove(key)来修改和删除元素， 会引发并发修改异常,可以通过迭代器的remove()： 

         *从迭代器指向的collection中移除当前迭代元素

         *来达到删除访问中的元素的目的。  

         **/
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");

        Iterator<Entry<Integer, String>> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, String> entry = it.next();
            int key = entry.getKey();
            if (key % 2 == 1) {
                System.out.println("delete this: " + key + " = " + key);
//                  map.put(key, "奇数"); //这样写会报异常  
//                  map.remove(key); //这样写会报异常
                it.remove(); //正确  
            }
        }
        //遍历最终的map。  
        System.out.println("-------\n\t最终的map的元素遍历：");
        for (Entry<Integer, String> entry : map2.entrySet()) {
            int k = entry.getKey();
            String v = entry.getValue();
            System.out.println(k + " = " + v);
        }
    }
}
