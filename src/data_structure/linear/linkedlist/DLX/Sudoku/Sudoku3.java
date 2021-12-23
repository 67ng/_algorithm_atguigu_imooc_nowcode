package data_structure.linear.linkedlist.DLX.Sudoku;

import data_structure.linear.linkedlist.DLX.DLX;

/**
 * @Description: BFX算法(舞蹈链)
 * @Date: 2021/12/23
 */

public class Sudoku3 {
    //编码需要根据图的性质进行调整，这个编码只针对对于9*9数独的等价限制条件
    public int[] encode(int i, int j, int k) {
        int r = (i * 9 + j) * 9 + k;
        int c1 = 81 * 0 + i * 9 + j + 1;
        int c2 = 81 * 1 + i * 9 + k;
        int c3 = 81 * 3 + j * 9 + k;
        int c4 = 81 * 2 + ((i / 3) * 3 + (j / 3)) * 9 + k;
        return new int[]{r, c1, c2, c3, c4};
    }

    public void solveSudoku(char[][] board) {
        DLX dlx = new DLX(9);
        dlx.init(9 * 9 * 9, 9 * 9 * 4);
        int[] code;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                for (int k = 1; k <= 9; k++)
                    if (board[i][j] == '.' || board[i][j] == '0' + k) {
                        code = encode(i, j, k);
                        dlx.link(code[0], code[1]);
                        dlx.link(code[0], code[2]);
                        dlx.link(code[0], code[3]);
                        dlx.link(code[0], code[4]);
                    }
        dlx.dance(0);
        int v, x, y;
        for (int i = 0; i < dlx.ansd; i++) {
            v = (dlx.ans[i] - 1) % 9 + 1;//这里记得加回1，从'1'~'9'
            x = (dlx.ans[i] - 1) / 9 / 9;//开始编码的，不是'0'~'8'
            y = (dlx.ans[i] - 1) / 9 % 9;//否则下面将采用(char)('1'+v)
            board[x][y] = (char) ('0' + v);
        }

    }


}
