package alg_01_ds_wyj._04_graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 11:54
 * @Version 1.0
 */
public class AdjList implements Graph {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public AdjList(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            this.adj = new LinkedList[V];
            // 初始化
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }

            while ((line = reader.readLine()) != null) {
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误！");
                }
                if (adj[a].contains(b)) {
                    throw new RuntimeException("出现了平行边，错误！");
                }

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(String.format("顶点 %d 不合格", v));
        }
    }

    @Override
    public int getE() {
        return E;
    }

    @Override
    public int getV() {
        return V;
    }

    @Override
    public boolean hadEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    @Override
    public int degree(int v) {
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append("顶点 " + v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjList = new AdjList("LC_douma/data/graph.txt");
        System.out.println(adjList);
    }
}
