package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 22:47
 * @Version 1.0
 */

// KeyPoint 以后刷题，遇到并查集问题，都使用这种解法 => 推荐使用
public class _09_05_UnionFind_Tree_Rank_Path implements _09_UnionFind {

    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;
    // rank[i] 表示以 i 为根的集合所表示的树的深度
    private int[] rank;

    public _09_05_UnionFind_Tree_Rank_Path(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找元素 p 所属的集合
    // KeyPoint 在 find 过程中，将路径压缩，减少树的高度，提高性能
    // 时间复杂度：O(h)，h 表示树的深度
    // O(h) => O(1) < O(log*n) < O(logn)
    // 补充说明：log* N位是一个迭代算法，它的增长非常慢，比log N慢得多
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            // 将长的(斜树) 压缩 高度较低树结构 => 路径压缩 => 将树的高度压缩
            // parent[p] => p 节点的父指针
            // parent[parent[p]] => p 父节点的父节点
            parent[p] = parent[parent[p]];
            // p 指针移动父节点
            p = parent[p];

            // KeyPoint 解释说明
            /*
              1.原始链表 => 压缩成高度较低树结构

                             5
                           ↗
                          3
                        ↗
                       1
                     ↗
                    2
                  ↗
                 4
                 ↑
                 p

               2.执行 parent[p] = parent[parent[p]]

                         5
                        ↗
                       3
                     ↗
                p → 1
                  ↗ ↖
                  2   4

               2.执行 parent[p] = parent[parent[p]]

                       p → 5
                        ↗  ↖
                       3     1
                           ↗ ↖
                           2   4

               3.p == parent[p] 结束 while 循环

             */

        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            // rank 表示树高度(深度)，而不是表示节点数
            // 合并之后，树的深度加 1
            rank[pRoot] += 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }
}
