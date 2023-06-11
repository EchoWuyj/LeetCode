package alg_02_train_wyj._22_day_回溯算法一.review;

import java.util.Collection;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 19:49
 * @Version 1.0
 */
public interface Graph {
    int getE();

    int getV();

    boolean hasEdge(int v, int w);

    Collection<Integer> adg(int v);

    int degree(int v);
}
