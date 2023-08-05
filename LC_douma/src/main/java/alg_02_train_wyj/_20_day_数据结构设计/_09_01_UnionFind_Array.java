package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 12:48
 * @Version 1.0
 */
public class _09_01_UnionFind_Array implements _09_UnionFind {
    int[] id;

    public _09_01_UnionFind_Array(int capacity) {
        id = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            id[i] = i;
        }
    }

    // 查找元素 p 的所属集合
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {

        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) id[i] = pId;
        }
    }

    @Override
    public int size() {
        return id.length;
    }
}
