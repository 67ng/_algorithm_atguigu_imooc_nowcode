package data_structure.linear.linkedlist.DLX.Sudoku;

import java.util.Arrays;

/**
 * @Description: 测试类
 * @Date: 2021/12/23
 */

public class Test {

    public static void print(char[][] board) {
        for (char[] chs : board)
            System.out.println(Arrays.toString(chs));
    }

    /**
     * +-----------------------------+
     * | 8       |         |         |
     * |       3 | 6       |         |
     * |    7    |    9    | 2       |
     * |---------+---------+---------|
     * |    5    |       7 |         |
     * |         |    4  5 | 7       |
     * |         | 1       |    3    |
     * |---------+---------+---------|
     * |       1 |         |    6  8 |
     * |       8 | 5       |    1    |
     * |    9    |         | 4       |
     * +-----------------------------+
     */
    public static void main(String[] args) {

        char[][] board = {
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '6', '.', '.', '.', '.', '.'},
                {'.', '7', '.', '.', '9', '.', '2', '.', '.'},
                {'.', '5', '.', '.', '.', '7', '.', '.', '.'},
                {'.', '.', '.', '.', '4', '5', '7', '.', '.'},
                {'.', '.', '.', '1', '.', '.', '.', '3', '.'},
                {'.', '.', '1', '.', '.', '.', '.', '6', '8'},
                {'.', '.', '8', '5', '.', '.', '.', '1', '.'},
                {'.', '9', '.', '.', '.', '.', '4', '.', '.'},
        };
        char[][] board1 = new char[9][9];
        char[][] board2 = new char[9][9];
        char[][] board3 = new char[9][9];
        System.arraycopy(board, 0, board1, 0, 9);
        System.arraycopy(board, 0, board2, 0, 9);
        System.arraycopy(board, 0, board3, 0, 9);

        Sudoku1 s1 = new Sudoku1();
        Sudoku2 s2 = new Sudoku2();
        Sudoku3 s3 = new Sudoku3();

        long time1 = System.nanoTime();
        s1.solveSudoku(board1);
        long time2 = System.nanoTime();
        s2.solveSudoku(board2);
        long time3 = System.nanoTime();
        s3.solveSudoku(board3);
        long time4 = System.nanoTime();
        System.out.println("回溯法耗时为： " + (time2 - time1) + "ns");
        System.out.println("DFX算法耗时为：" + (time4 - time3) + "ns");
        System.out.println("总理算法耗时为：" + (time3 - time2) + "ns");

        print(board1);
        System.out.println();
        print(board2);
        System.out.println();
        print(board3);
    }
}
