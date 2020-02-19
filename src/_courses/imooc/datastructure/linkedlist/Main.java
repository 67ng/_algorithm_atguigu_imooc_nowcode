package _courses.imooc.datastructure.linkedlist;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/11/25 19:34
 */
public class Main {
    public static void main(String[] args) {

        LinkedlistDemo<Integer> linkedList = new LinkedlistDemo<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
