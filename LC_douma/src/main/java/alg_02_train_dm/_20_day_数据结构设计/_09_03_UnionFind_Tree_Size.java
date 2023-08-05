package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 20:41
 * @Version 1.0
 */
public class _09_03_UnionFind_Tree_Size implements _09_UnionFind {

    // 存在问题：树形结构退化成链表
    // 如：
    //    3
    //    ↑
    //    2
    //    ↑
    //    1
    //    0
    // UnionFind_Tree 的 find，isConnected，unionElement 操作 => 时间复杂度O(n)

    // KeyPoint 优化：考虑树的 size

    // parent[i] 表示的是节点 i 所指向的父亲节点
     int[] parent;
    // size[i] 表示的是以节点 i 为根节点的子树的所有节点数
     int[] size;

    public _09_03_UnionFind_Tree_Size(int capacity) {
        parent = new int[capacity];
        size = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            // 最开始，每颗子树只有一个节点
            size[i] = 1;
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h)，其中 h 表示树的深度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // KeyPoint 优化
    // 1.合并时考虑节点数量，将节点数量小树 => 合并 => 节点数量大树
    // 2.降低整颗合并之后树的高度，通过降低树的高度，从而提高 find 性能
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        // "小往大"调整
        if (size[pRoot] < size[qRoot]) {
            // 1.root 调整(从左往右)
            // pRoot 父节点 => parent[pRoot]
            parent[pRoot] = qRoot;
            // 2.节点个数合并(从右往左)
            size[qRoot] += size[pRoot];
        } else { // size[pRoot] >= size[qRoot]
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }

    @Override
    public int size() {
        return parent.length;
    }
}
