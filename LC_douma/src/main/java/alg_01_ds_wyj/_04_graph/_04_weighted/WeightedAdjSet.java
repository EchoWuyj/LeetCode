package alg_01_ds_wyj._04_graph._04_weighted;

import alg_01_ds_dm._04_graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-24 12:05
 * @Version 1.0
 */
public class WeightedAdjSet implements Graph {
    private int V;
    private int E;
    private TreeMap<Integer, Integer>[] adj;

    public WeightedAdjSet(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            this.adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            while ((line = reader.readLine()) != null) {
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误");
                }
                if (adj[a].containsKey(b)) {
                    throw new RuntimeException("出现了平行边，错误");
                }

                int weight = Integer.parseInt(arr[2]);
                adj[a].put(b, weight);
                adj[b].put(a, weight);
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
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        return -1;
    }

    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
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
            sb.append(v + ": ");
            TreeMap<Integer, Integer> adjMap = adj[v];
            for (Map.Entry<Integer, Integer> entry : adjMap.entrySet()) {
                sb.append("(" + entry.getKey() + "," + entry.getValue() + ") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedAdjSet adjList = new WeightedAdjSet("LC_douma/data/weighted-graph.txt");
        System.out.println(adjList);
    }
}
