package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-08 17:03
 * @Version 1.0
 */
public class _06_Knapsack2 {

    private int[] w;
    private int[] v;

    public int knapsack(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        return dfs(-1, C);
    }

    public int dfs(int index, int C) {
        int maxValue = 0;
        for (int i = index; i < w.length; i++) {
            int childIndex = i + 1;
            if (childIndex == w.length || C - w[childIndex] < 0) continue;
            int childMaxValue = dfs(childIndex, C - w[childIndex]);
            maxValue = Math.max(maxValue, childMaxValue);
        }
        return maxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _06_Knapsack2 k = new _06_Knapsack2();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};

        System.out.println(k.knapsack(w, v, 10));
    }
}
