package alg_02_train_dm._22_day_回溯算法一.review;

import java.util.Collection;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 19:18
 * @Version 1.0
 */
public interface Graph {
    // 获取图的边数
    int getE();

    // 获取图的顶点数
    int getV();

    // 判断两个指定的顶点之间是否有边
    boolean hasEdge(int v, int w);

    // 获取指定顶点所有相邻的顶点
    Collection<Integer> adj(int v);

    // 获取指定顶点的度数
    int degree(int v);
}
