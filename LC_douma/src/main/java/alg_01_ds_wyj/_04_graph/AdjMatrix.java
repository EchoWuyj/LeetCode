package alg_01_ds_wyj._04_graph;

import com.sun.javafx.scene.text.HitInfo;
import javafx.scene.chart.ValueAxis;
import jdk.nashorn.api.scripting.AbstractJSObject;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.awt.event.HierarchyBoundsAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 10:47
 * @Version 1.0
 */
public class AdjMatrix implements Graph {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] arr = line.split(" ");
            this.V = Integer.parseInt(arr[0]);
            this.E = Integer.parseInt(arr[1]);
            this.adj = new int[V][V];

            while ((line = reader.readLine()) != null) {
                arr = line.split(" ");
                int a = Integer.parseInt(arr[0]);
                validateVertex(a);
                int b = Integer.parseInt(arr[1]);
                validateVertex(b);
                // 检测自环边
                if (a == b) {
                    throw new RuntimeException("出现了自环边，错误！");
                }
                // 检测平行边
                if (adj[a][b] == 1) {
                    throw new RuntimeException("出现了平行边，错误！");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    @Override
    public Collection<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    @Override
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数 = %d，边数 = %d \n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(adj[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("LC_douma/data/graph.txt");
        System.out.println(adjMatrix);
    }
}
