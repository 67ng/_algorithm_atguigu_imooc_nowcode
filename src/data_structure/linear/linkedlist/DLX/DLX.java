package data_structure.linear.linkedlist.DLX;

/**
 * @Description: 舞蹈链的模板
 * @Date: 2021/12/23
 */

public class DLX {
    public int n, m, si;//n行数m列数si目前有的节点数
    public int[] U, D, L, R, Row, Col;//结点信息 [MAXNODE]
    public int[] H, ans;
    public int ansd;//H[r] 第r行头指针 ans[d]记录答案，当前搜索（深度）行 [MAXN]
    public int[] S;//S[c] 第c列存在结点数 [MAXM]

    public DLX(int n) {
        int maxn = n * n * n + 10;
        int maxm = n * n * 4 + 10;
        int maxnode = maxn * 4 + maxm;
        L = new int[maxnode];
        R = new int[maxnode];
        Col = new int[maxnode];
        U = new int[maxnode];
        D = new int[maxnode];
        Row = new int[maxnode];
        H = new int[maxn];
        ans = new int[maxn];
        S = new int[maxm];
    }

    public void init(int n, int m) {  //初始化空表
        this.n = n;
        this.m = m;
        for (int i = 0; i <= m; i++) { //初始化第一横行（表头）
            S[i] = 0;
            U[i] = D[i] = i;//目前纵向的链是空的
            L[i] = i - 1;
            R[i] = i + 1; //横向的连起来
        }
        R[m] = 0;
        L[0] = m;
        si = m;
        for (int i = 1; i <= n; i++) H[i] = -1;
    }

    public void link(int r, int c) {    //插入点(r,c)
        ++S[Col[++si] = c];
        Row[si] = r;
        D[si] = D[c];
        U[D[c]] = si;
        U[si] = c;
        D[c] = si;//核心代码.1上下连表
        if (H[r] == -1) H[r] = L[si] = R[si] = si;
        else {
            R[si] = R[H[r]];
            L[R[H[r]]] = si;
            L[si] = H[r];
            R[H[r]] = si;
        }
        //核心代码.2左右连表
    }

    public void remove(int c) {//列表中删掉c列
        L[R[c]] = L[c];//表头操作
        R[L[c]] = R[c];
        for (int i = D[c]; i != c; i = D[i])
            for (int j = R[i]; j != i; j = R[j]) {
                U[D[j]] = U[j];
                D[U[j]] = D[j];
                --S[Col[j]];
            }
    }

    public void resume(int c) {//恢复c列
        for (int i = U[c]; i != c; i = U[i])
            for (int j = L[i]; j != i; j = L[j])
                ++S[Col[U[D[j]] = D[U[j]] = j]];
        L[R[c]] = R[L[c]] = c;
    }

    public boolean dance(int d) { //选取了d行
        if (R[0] == 0) {
            ansd = d;
            return true;
        }//全部覆盖了
        int c = R[0];
        for (int i = R[0]; i != 0; i = R[i]) if (S[i] < S[c]) c = i;
        remove(c);
        for (int i = D[c]; i != c; i = D[i]) {
            ans[d] = Row[i];
            for (int j = R[i]; j != i; j = R[j]) remove(Col[j]);
            if (dance(d + 1)) return true;
            for (int j = L[i]; j != i; j = L[j]) resume(Col[j]);
        }
        resume(c);
        return false;
    }
}
