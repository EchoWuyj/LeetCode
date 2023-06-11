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
        int maxValue = 0;

        for (int i = index; i < w.length; i++) {
            int childIndex = i;
            if (childIndex == -1
                    || childIndex == w.length
                    || capacity < w[childIndex]) continue;
            int childMaxValue = dfs(childIndex, capacity - w[childIndex]);
            maxValue = Math.max(maxValue, childMaxValue);
        }
        return maxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _06_CompleteKnapsack1 k = new _06_CompleteKnapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsackComplete(w, v, 10)); // 45
    }
}
