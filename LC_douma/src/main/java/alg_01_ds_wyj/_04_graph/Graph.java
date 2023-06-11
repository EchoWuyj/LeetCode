package alg_01_ds_wyj._04_graph;

import java.util.Collection;

/**
 * @Author Wuyj
 * @DateTime 2023-03-21 10:48
 * @Version 1.0
 */
public interface Graph {
    int getE();

    int getV();

    boolean hadEdge(int v, int w);

    Collection<Integer> adj(int v);

    int degree(int v);
}
