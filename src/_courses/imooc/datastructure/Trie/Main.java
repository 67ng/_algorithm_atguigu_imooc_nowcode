package _courses.imooc.datastructure.Trie;

/**
 * Description:
 *
 * @date: 2018/11/28 19:49
 */

import _courses.imooc.datastructure.Set_Map.Set.BSTSet;
import _courses.imooc.datastructure.Set_Map.Set.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("D:\\视频\\算法\\imooc算法\\算法1：玩转数据结构 从入门到进阶\\源码\\07-Set-and-Map\\02-LinkedListSet\\pride-and-prejudice.txt", words)) {

            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");

            // ---

            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}

