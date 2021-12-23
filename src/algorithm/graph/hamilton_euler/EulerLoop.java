package algorithm.graph.hamilton_euler;

import algorithm.graph.*;
import algorithm.graph.DFS.ConnectedComponent;

/**
 *
 * 欧拉回路的充分必要条件每个顶点的度数都是偶数
 *
 * 引理：
 * 1.每个顶点度数全是偶数，去掉一个环，剩下的每个顶点度数还是偶数
 * 2.两个相连的环（EulerLoop无向联通图环必相连），一定可以组成一个新环
 *
* */
public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph G){
        this.G = G;
    }

    private boolean hasEulerLoop(){

        ConnectedComponent cc = new ConnectedComponent(G);
        if(cc.count() > 1) return false;//联通分量大于1

        for(int v = 0; v < G.V(); v ++)
            if(G.degree(v) % 2 == 1) return false;
        return true;
    }
}
