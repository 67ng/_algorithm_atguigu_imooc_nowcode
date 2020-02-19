package _courses.imooc.datastructure.HashTable;

/**
 * Description:哈希函数性质
 * 1.一致性，相同参数得到哈希值相同；
 * 2.高效性；
 * 3.均匀性，得到所有哈希值的概率相等；
 *
 * @date: 2018/11/30 10:31
 */

import java.util.HashSet;
import java.util.HashMap;

public class Main1 {

    public static void main(String[] args) {

        int a = 42;
        System.out.println(((Integer) a).hashCode());

        int b = -42;
        System.out.println(((Integer) b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double) c).hashCode());

        String d = "_courses/imooc";
        System.out.println(d.hashCode());

        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println();

        Student student = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student2 = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student2.hashCode());
    }
}

