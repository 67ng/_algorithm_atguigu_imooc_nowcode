package imooc.Graph_Algorithms.Graph_Basics.AdjacencySet.src;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Scanner;
/**
 *
 * 时间复杂度O（Elog(V)）,空间复杂度O（E+V）
 * */
public class AdjSet {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;//用红黑树代替链表，相比hashSet，保持有序，更节省空间

    public AdjSet(String pathStr){

        File file = new File(pathStr);

        try(Scanner scanner = new Scanner(file)){

            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet[V];
            for(int i = 0; i < V; i ++)
                adj[i] = new TreeSet<Integer>();

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].add(b);
                adj[b].add(a);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v){//屏蔽实现细节
    // public TreeSet<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int v = 0; v < V; v ++){
            sb.append(String.format("%d : ", v));
            for(int w : adj[v])
                sb.append(String.format("%d，", w));
            sb.deleteCharAt(sb.length()-1);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){

        AdjSet adjSet = new AdjSet("C:\\Users\\mzh\\IdeaProject\\_justforfun\\src\\imooc\\Graph_Algorithms\\Graph_Basics\\AdjacencySet\\g.txt");
        System.out.print(adjSet);
    }
}
