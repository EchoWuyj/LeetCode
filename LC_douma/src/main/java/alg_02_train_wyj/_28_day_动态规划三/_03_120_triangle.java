package alg_02_train_wyj._28_day_动态规划三;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-11 16:51
 * @Version 1.0
 */
public class _03_120_triangle {

    private int minPathSum = Integer.MAX_VALUE;
    private int size;

    public int minimumTotal(List<List<Integer>> triangle) {
        size = triangle.size();
        dfs(triangle, 0, 0, triangle.get(0).get(0));
        return minPathSum;
    }

    private void dfs(List<List<Integer>> triangle, int i, int j, int pathSum) {
        if (i == size) {
            minPathSum = Math.min(minPathSum, pathSum);
            return;
        }
        dfs(triangle, i + 1, j,
                pathSum + (i + 1 == size ? 0 : triangle.get(i + 1).get(j)));
        dfs(triangle, i + 1, j + 1,
                pathSum + (i + 1 == size ? 0 : triangle.get(i + 1).get(j + 1)));
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] memo = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return dfs(triangle, 0, 0, memo);
    }

    public int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];

        int left = dfs(triangle, i + 1, j, memo);
        int right = dfs(triangle, i + 1, j + 1, memo);
        memo[i][j] = triangle.get(i).get(j) + Math.min(left, right);
        return memo[i][j];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
