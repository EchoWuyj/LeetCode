package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-19 21:45
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

    public void dfs1(int index, int capacity, int curValue) {
        if (capacity < 0) {
            return;
        }

        maxValue = Math.max(maxValue, curValue);
        for (int i = index; i < w.length; i++) {
            int subIndex = i + 1;
            if (subIndex == w.length) continue;
            dfs1(subIndex, capacity - w[subIndex], curValue + v[subIndex]);
        }
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack1_bak k = new _06_0_1_Knapsack1_bak();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }
}
