package alg_01_ds_dm._04_graph;

import java.util.Collection;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 19:57
 * @Version 1.0
 */
public interface Graph {
    /**
     * 获取图的边数
     *
     * @return
     */
    int getE();

    /**
     * 获取图的顶点数
     *
     * @return
     */
    int getV();

    /**
     * 判断两个指定的顶点之间是否有边
     *
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v, int w);

    /**
     * 获取指定顶点所有相邻的顶点
     *
     * @param v
     * @return
     */
    Collection<Integer> adj(int v);

    /**
     * 获取指定顶点的度数
     *
     * @param v
     * @return
     */
    int degree(int v);
}
