package imooc.Graph_Algorithms.Euler_Loop_and_Euler_Path.EulerLoopDetection.src;

public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph G){
        this.G = G;
    }

    private boolean hasEulerLoop(){

        CC cc = new CC(G);
        if(cc.count() > 1) return false;

        for(int v = 0; v < G.V(); v ++)
            if(G.degree(v) % 2 == 1) return false;
        return true;
    }
}
