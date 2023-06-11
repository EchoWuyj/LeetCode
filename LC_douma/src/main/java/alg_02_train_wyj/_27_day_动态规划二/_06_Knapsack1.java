package alg_02_train_wyj._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-07 14:46
 * @Version 1.0
 */
public class _06_Knapsack1 {
    private int[] w;
    private int[] v;
    private int maxValue = 0;

    public int knapsack(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        dfs(-1, C, 0);
        return maxValue;
    }

    private void dfs(int index, int c, int curValue) {
        maxValue = Math.max(maxValue, curValue);

        for (int i = index; i < w.length; i++) {
            int childIndex = i + 1;
            if (childIndex == w.length || c < w[childIndex]) continue;
            dfs(childIndex, c - w[childIndex], curValue + v[childIndex]);
        }
    }

    public static void main(String[] args) {
        _06_Knapsack1 k = new _06_Knapsack1();
        int[] w = {3, 4, 5};
        int[] v = {15, 10, 12};
        System.out.println(k.knapsack(w, v, 10)); // 27
    }


}
