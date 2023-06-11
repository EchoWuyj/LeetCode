package alg_02_train_dm._27_day_动态规划二._06_knapsack;

import alg_02_train_wyj._27_day_动态规划二._06_Knapsack2;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 20:33
 * @Version 1.0
 */
public class _06_0_1_Knapsack2 {
    private int[] w;
    private int[] v;

    public int knapsack(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        return dfs(-1, C);
    }

    // DFS 后序遍历 => 从子节点中获取信息，再返回父节点统计计算
    // => 刚开始，遍历到最左节点，执行代码逻辑，然后右节点，最后返回父节点，按照这个顺序，组织代码逻辑
    // 抽象数结构，节点构成 [物品索引，剩余容量]，故 dfs 形参为这两个参数
    // dp => [物品索引，剩余容量] => 状态参数
    // 递归函数含义：在容量为 c 的背包里放入第 index 号物品得到的最大价值
    private int dfs(int index, int c) {
        // 定义 maxValue 变量
        int maxValue = 0;

        // 从 index 开始是为了控制顺序
        for (int i = index; i < w.length; i++) {
            // 处理子节点
            int childIndex = i + 1;
            if (childIndex == w.length) continue;
            if (c < w[childIndex]) continue;
            // 递归调用子节点，获取子节点的最大价值
            int childMaxValue = dfs(childIndex, c - w[childIndex]);
            // 遍历子节点中，挑选出最大价值
            maxValue = Math.max(maxValue, childMaxValue);
        }
        // 父节点计算逻辑：父节点最大价值 = 父节点价值 + 子节点中最大价值
        // 特殊 root，需要特判一下，没有物品价值，直接累加 0
        return maxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack2 k = new _06_0_1_Knapsack2();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsack(w, v, 10)); // 27
    }
}
