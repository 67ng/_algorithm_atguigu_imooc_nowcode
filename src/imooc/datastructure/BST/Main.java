package imooc.datastructure.BST;

/**
 * Description:测试BST的遍历
 *
 * @date: 2018/11/26 17:06
 */
public class Main {

    public static void main(String[] args) {

            BST<Integer> bst = new BST<>();
            int[] nums = {5, 3, 6, 8, 4, 2};
            for (int num : nums)
                bst.add(num);

            /////////////////
            //      5      //
            //    /   \    //
            //   3    6    //
            //  / \    \   //
            // 2  4     8  //
            /////////////////
            bst.preOrder();
            System.out.println();

            bst.inOrder();
            System.out.println();

            bst.postOrder();
            System.out.println();

            bst.levelOrder();
            System.out.println();
        }
    }
