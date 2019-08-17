package imooc.datastructure.array;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/11/23 22:30
 */
public class Main {
    public static void main(String[] args) {

        Dynamic_Generic_ArrayDemo<Integer> arr = new Dynamic_Generic_ArrayDemo<>();
        for (int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }
}
