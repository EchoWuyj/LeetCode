package alg_02_train_wyj._28_day_动态规划三;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-11 16:51
 * @Version 1.0
 */
public class _03_120_triangle {
    private int minPath = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        dfs(triangle, 0, 0, triangle.get(0).get(0));
        return minPath;
    }

    public void dfs(List<List<Integer>> triangle, int i, int j, int path) {
        if (i == triangle.size()) {
            minPath = Math.min(minPath, path);
            // 返回值 void，不要忘了 return
            return;
        }

        dfs(triangle, i + 1, j, path + (i + 1 != triangle.size() ? triangle.get(i + 1).get(j) : 0));
        dfs(triangle, i + 1, j + 1, path + (i + 1 != triangle.size() ? triangle.get(i + 1).get(j + 1) : 0));
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(triangle, 0, 0, memo);
    }

    public int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != -1) return memo[i][j];
        int left = dfs(triangle, i + 1, j, memo);
        int right = dfs(triangle, i + 1, j + 1, memo);
        memo[i][j] = Math.min(left, right) + triangle.get(i).get(j);
        return memo[i][j];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
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

        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
