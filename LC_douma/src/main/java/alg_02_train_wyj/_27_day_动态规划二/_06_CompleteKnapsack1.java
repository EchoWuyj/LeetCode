package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 10:43
 * @Version 1.0
 */
public class _06_CompleteKnapsack1 {
    private int[] w;
    private int[] v;

    public int knapsackComplete(int[] w, int[] v, int capacity) {
        this.w = w;
        this.v = v;
        return dfs(-1, capacity);
    }

    public int dfs(int index, int capacity) {
        int bestSubMaxValue = 0;
        for (int i = index; i < w.length; i++) {
            int subIndex = i;
            if (subIndex == -1 || subIndex == w.length || capacity < w[subIndex]) continue;
            int subMaxValue = dfs(subIndex, capacity - w[subIndex]);
            bestSubMaxValue = Math.max(bestSubMaxValue, subMaxValue);
        }
        return bestSubMaxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack1 k = new _06_CompleteKnapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsackComplete(w, v, 10)); // 45
    }
}
