package imooc.datastructure.AVL;

/**
 * Description:
 *
 * @date: 2018/11/29 17:12
 */

import imooc.datastructure.Set_Map.Map.BSTMap;
import imooc.datastructure.Set_Map.Map.LinkedListMap;
import imooc.datastructure.Set_Map.Set.FileOperation;

import java.util.ArrayList;


public class TestMapMain {

    public static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "D:\\视频\\算法\\imooc算法\\算法1：玩转数据结构 从入门到进阶\\源码\\07-Set-and-Map\\02-LinkedListSet\\pride-and-prejudice.txt";

       /* BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + " s");*/

        System.out.println();

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("AVL Map: " + time3 + " s");
    }
}

