package alg_02_train_dm._20_day_数据结构设计_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 20:01
 * @Version 1.0
 */
public class _09_01_UnionFind_Array implements _09_UnionFind {

    // id[i] 表示的是顶点 i 所属的集合
    // index => 顶点 i
    // value => i 顶点所属集合
     int[] id;

    public _09_01_UnionFind_Array(int capacity) {
        // 初始化
        id = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 刚开始每个顶点都是一个独立集合
            id[i] = i;
        }
    }

    // 函数功能：查找元素 p 所属的集合
    // 时间复杂度 O(1)
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        return id[p];
    }

    // 时间复杂度 O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 函数功能：合并操作，合并两个元素到一个集合中
    // 时间复杂度 O(n)
    // KeyPoint 缺点：在数据规模大，且 unionElement 调用次数多，则比较消耗性能，需要优化
    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        // pId 和 qId，在同一个集合，直接 return，说明已经是同一个集合
        if (pId == qId) return;

        // KeyPoint 注意事项
        // 1.对于数组顺序访问，JVM 会优化，在 CPU 层面有优化
        // 2.具体来说：数组是整块的，相邻元素先会放到 CPU 缓存中
        //            此时数组顺序访问，只要从缓存获取即可，速度比较快
        // 时间复杂度 O(n)
        int n = id.length;
        for (int i = 0; i < n; i++) {
            // 遍历整个集合，将 id 值为 qId 值赋值成 pId，从而保证是同一个集合
            if (id[i] == qId) id[i] = pId;
        }
    }

    @Override
    public int size() {
        // 数组长度
        return id.length;
    }
}
