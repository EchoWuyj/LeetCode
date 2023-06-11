package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 12:46
 * @Version 1.0
 */
public interface _09_UnionFind {
    void unionElement(int p, int q);

    boolean isConnected(int p, int q);

    int size();
}
