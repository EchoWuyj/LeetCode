package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 17:03
 * @Version 1.0
 */
public class _06_0_1_Knapsack2 {

    private int[] w;
    private int[] v;

    public int knapsack(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        return dfs(-1, capacity);
    }

    public int dfs(int index, int capacity) {
        int maxValue = 0;
        for (int i = index; i < w.length; i++) {
            int subIndex = i + 1;
            if (subIndex == w.length || capacity < w[subIndex]) continue;
            int subMaxValue = dfs(subIndex, capacity - w[subIndex]);
            maxValue = Math.max(maxValue, subMaxValue);
        }
        return (index == -1 ? 0 : v[index]) + maxValue;
    }

    public static void main(String[] args) {
        _06_0_1_Knapsack2 k = new _06_0_1_Knapsack2();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsack(w, v, 10));
    }
}
