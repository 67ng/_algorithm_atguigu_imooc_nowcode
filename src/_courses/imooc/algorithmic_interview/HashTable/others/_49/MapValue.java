package _courses.imooc.algorithmic_interview.HashTable.others._49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:map中value值的更新
 *
 * @date: 2019/2/3 23:59
 */
public class MapValue {
    //ArrayList类型
    private static ArrayList<String> arrayListDemo() {
        Map<Integer, ArrayList<String>> map2 = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("asd");
        map2.put(1, arrayList);
        map2.get(1).add("qwe");
        return map2.get(1);
    }

    //int类型
    private static int anIntDemo() {
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(3, 7);
        return map1.get(3);
    }

    public static void main(String[] args) {
        System.out.println(MapValue.arrayListDemo().toString());
        System.out.println(MapValue.anIntDemo());
    }
}
