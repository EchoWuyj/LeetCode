package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 14:46
 * @Version 1.0
 */
public class _06_0_1_Knapsack1 {
    private int[] w;
    private int[] v;
    private int maxValue;

    public int knapsack(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        dfs(-1, capacity, 0);
        return maxValue;
    }

    public void dfs(int index, int capacity, int curValue) {
        maxValue = Math.max(maxValue, curValue);
        for (int i = index; i < w.length; i++) {
            int subIndex = i + 1;
            if (subIndex == w.length || capacity < w[subIndex]) continue;
            dfs(subIndex, capacity - w[subIndex], curValue + v[subIndex]);
        }
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack1 k = new _06_0_1_Knapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }
}
