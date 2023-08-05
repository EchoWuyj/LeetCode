package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 19:56
 * @Version 1.0
 */
public interface _09_UnionFind {

    // 并查集抽象成接口，其实现有多个

    // KeyPoint 并查集核心两个操作
    // 1.合并操作：合并两个元素到一个集合中
    // 2.判断两个元素是否连接

    // 将两个顶点连接
    void unionElement(int p, int q);

    // 判断两个顶点是否连接
    boolean isConnected(int p, int q);

    // 返回当前并查集中顶点的个数
    int size();
}
