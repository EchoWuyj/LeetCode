package alg_02_train_dm._27_day_动态规划二_2刷._06_knapsack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-19 21:34
 * @Version 1.0
 */
public class _06_0_1_Knapsack1_bak {

    private int[] w;
    private int[] v;
    // 没有赋初值，则为默认值，int 默认值是 0
    private int maxValue = 0;

    public int knapsack(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        // 索引从 -1 开始，不能从 0 开始，相当于 root 为 -1
        dfs1(-1, capacity, 0);
        return maxValue;
    }

    // DFS 先序遍历 => 传统形式 dfs => 没有完全剪枝
    private void dfs1(int index, int capacity, int curValue) {

        // 注意：调用 dfs1 时，传入的形参 capacity - w[childIndex] 已经减过了，
        // 不需要再减一遍了，只需要判断 capacity 是否 >= 0，若 < 0，则直接返回
        if (capacity < 0) return;

        // if (index == w.length || capacity < w[index]) return;
        // 若 index == w.length 作为递归边界，在调用 dfs1 时，w[subIndex] 或者 v[subIndex] 已经越界了

        maxValue = Math.max(maxValue, curValue);
        for (int i = index; i < w.length; i++) {
            int subIndex = i + 1;
            if (subIndex == w.length) continue;
            // 在调用 w[subIndex]，若不判断 subIndex，subIndex == w.length
            // w[subIndex] 必然越界，故必须进行 if 判断，提前剪枝
            // 测试用例报错：数组长度为 3 ArrayIndexOutOfBoundsException: 3
            dfs1(subIndex, capacity - w[subIndex], curValue + v[subIndex]);
        }
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack1 k = new _06_0_1_Knapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }
}
