package simple_fun.algorithm.graph.hamilton_euler;

import simple_fun.algorithm.graph.*;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Description: 寻找欧拉回路：
 * 1.回溯法，指数级
 * 2.Fleury算法，有多条边选择的时候，不走桥，类似贪心算法（注意：每走一条边删除该边再判断接下的边是否是桥），时间复杂度O（E^2）
 * 3.Hierholzer算法，核心思想：通过构造一系列相连的环构造出欧拉回路，时间复杂度O（V+E）
 *
 * @Author: caffebaby
 * @Date: 2020/3/8
 */
public class GetEulerLoop {
    private Graph G;

    public GetEulerLoop(Graph G) {
        this.G = G;
    }

    private boolean hasEulerLoop() {

        ConnectedComponent cc = new ConnectedComponent(G);
        if (cc.count() > 1) return false;

        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 == 1) return false;
        return true;
    }

//    Hierholzer算法
    public ArrayList<Integer> result() {

        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;

        Graph g = (Graph) G.clone();//对原图进行深拷贝

        Stack<Integer> stack = new Stack<>();
        int curv = 0;//从顶点0开始遍历
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (g.degree(curv) != 0) {//当前顶点的度数不为0还有路可走
                stack.push(curv);
                int w = g.adj(curv).iterator().next();//选择下一个顶点
                g.removeEdge(curv, w);//删除遍历过的边，减度数
                curv = w;
            } else {//无路可走就是找到一个环
                res.add(curv);
                curv = stack.pop();//回退，直到退到一个顶点度数不为0的顶点，进入前面的if继续寻找新环
            }
        }
        return res;
    }
}
