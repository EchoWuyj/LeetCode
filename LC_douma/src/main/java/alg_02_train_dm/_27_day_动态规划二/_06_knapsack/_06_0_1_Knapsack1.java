package alg_02_train_dm._27_day_动态规划二._06_knapsack;

import alg_02_train_wyj._27_day_动态规划二._06_Knapsack1;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 20:12
 * @Version 1.0
 */
public class _06_0_1_Knapsack1 {

    /*
        0-1 背包问题
        有一个背包，它的容量为 C
        现在有 n 种不同的物品，他们的编号分别是 0，...，n-1，每一种物品只有一个
        在这 n 种物品中，第 i 个物品的重量是 w[i]，它的价值为 v[i]
        问题是：可以向这个背包中放哪些物品，使得在不超过背包容量的基础上，背包中物品的总价值最大

        KeyPoint 补充：解释 0-1
        每一种物品只有一个，在做选择时，要么选择该物品，要么不选择该物品，从而体现 0-1
     */

    private int[] w;
    private int[] v;
    private int maxValue = 0;

    public int knapsack(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        // 索引从 -1 开始，不能从 0 开始，相当于 root 为 -1
        dfs(-1, C, 0);
        return maxValue;
    }

    // DFS 先序遍历 => 没法转 dp，需要后序 DFS 遍历
    // 抽象树形结构，节点构成 [物品索引，剩余容量，累计价值]，故 dfs 形参为这三个参数
    private void dfs(int index, int c, int currValue) {
        // 1.先序遍历，处理当前节点(父节点)
        // 针对遍历到的每个节点都比较是否为最大值
        maxValue = Math.max(maxValue, currValue);

        // dfs 存在重复计算，需要剪枝
        // 如：红蓝，蓝红，只是顺序相反，其余是一样的，而背包是不考虑顺序的，故存在重复计算

        // 2.处理子节点
        // 通过：子节点物品索引 > 父节点物品索引，避免重复计算 => 剪枝
        for (int i = index; i < w.length; i++) {
            // index 只是起始值，循环变量是 i
            int childIndex = i + 1;
            // 剪枝 => 避免无效递归
            if (childIndex == w.length || c < w[childIndex]) continue;
            // 在 dfs前，先做判断，通过'剪枝'方式代替了递归边界
            dfs(childIndex, c - w[childIndex], currValue + v[childIndex]);
        }
    }

    // 传统形式 dfs => 没有完全剪枝
    private void dfs1(int index, int c, int curValue) {
        if (index != -1) {
            // c - w[childIndex] 已经减过了，只需要判断其是否 >= 0，若 < 0，则直接返回
//            if (index == w.length || c < w[index]) return;
            if (c < 0) return;
        }
        maxValue = Math.max(maxValue, curValue);
        for (int i = index; i < w.length; i++) {
            int childIndex = i + 1;
            // 在调用 w[childIndex]，不判断 childIndex，则索引可能越界
            // => 数组长度为 3 ArrayIndexOutOfBoundsException: 3
            // 必须判断，剪枝，否则 dfs1 中 w[childIndex] 必然越界
            if (childIndex == w.length) continue;
            dfs1(childIndex, c - w[childIndex], curValue + v[childIndex]);
        }
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack1 k = new _06_0_1_Knapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }
}
