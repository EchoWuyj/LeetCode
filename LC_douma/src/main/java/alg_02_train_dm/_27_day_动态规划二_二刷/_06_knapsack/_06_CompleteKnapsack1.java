package alg_02_train_dm._27_day_动态规划二_二刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 18:54
 * @Version 1.0
 */
public class _06_CompleteKnapsack1 {

    /*
        完全背包
            有 n 种物品和一个容量为 C 的背包
            第 i 种物品的重量是 w[i]，价值是 v[i]，件数是无限
        求将哪些物品装入背包可使得价值总和最大

        KeyPoint 区别
        0-1背包： 每一种物品只有一个，在做选择时，要么选择该物品，要么不选择该物品
        完全背包：每件物品可以取无限次
     */

    private int[] w;
    private int[] v;

    private int max = 0;

    public int knapsackComplete(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        return dfs(-1, capacity);
    }

    // 在容量为 capacity 的背包里放入第 index 号物品，得到的最大价值
    // int[] w 和 int[] v 是全局变量，故不需要作为形参传入
    private int dfs(int index, int capacity) { // 状态参数
        // 父节点所有分支中，子节点最大值中的最大值
        int bestSubMaxValue = 0;

        // 从 index 开始是为了控制顺序
        for (int i = index; i < w.length; i++) {

            // KeyPoint 核心区别
            // 1.0-1背包：子节点物品索引 > 父节点物品索引 (严格大于)
            // 2.完全背包：子节点物品索引 >= 父节点物品索引，但是子节点不能选祖父节点索引
            // 子节点索引
            int subIndex = i;

            // 剪枝 => 剔除无效 dfs
            // 添加 subIndex = -1， 否则 w[subIndex] 越界
            if (subIndex == -1
                    || subIndex == w.length
                    || capacity < w[subIndex]) continue;

            // 子节点索引：subIndex，不是 i+1
            int subMaxValue = dfs(subIndex, capacity - w[subIndex]);
            bestSubMaxValue = Math.max(bestSubMaxValue, subMaxValue);
        }

        return bestSubMaxValue + (index == -1 ? 0 : v[index]);
    }

    // 完全背包回溯法 => 另一种实现 => 了解即可
    private void dfs1(int price, int c) {
        if (c <= 0) {
            return;
        }
        max = Math.max(max, price);
        for (int i = 0; i < w.length; i++) {
            dfs1(v[i] + price, c - w[i]);
        }
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack1 k = new _06_CompleteKnapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsackComplete(w, v, 10)); // 45
    }
}
