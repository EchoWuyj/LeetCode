package alg_02_体系班_wyj.class21;

import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceSubTreeData;

import java.rmi.MarshalException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 18:18
 * @Version 1.0
 */
public class Code01_MinPathSum {

    // 递归版本
    public static int minPathSum(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        return process(m, 0, 0);
    }

    // 到达最后位置时的最小路径和
    public static int process(int[][] matrix, int x, int y) {
        int n = matrix.length;
        int m = matrix[0].length;

        // 递归边界
        if (x == n - 1 && y == m - 1) {
            // 当前位置已经在右下角了
            return matrix[x][y];
        }

        // 在下边界和右边界时,限制(x,y)走向,自然不用考虑越界情况
        // 当前位置处在最后一行时,只能向右走
        if (x == n - 1) {
            return matrix[x][y] + process(matrix, x, y + 1);
        }

        // 当前位置处在最后一列时,只能向下走
        if (y == m - 1) {
            return matrix[x][y] + process(matrix, x + 1, y);
        }

        // 普通位置:取左边位置和右边位置到右下角位置距离最小的那个
        int right = matrix[x][y] + process(matrix, x, y + 1);
        int down = matrix[x][y] + process(matrix, x + 1, y);

        return Math.min(right, down);
    }

    // dp(自己修改)
    public static int dp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = matrix[n - 1][m - 1];

        // 注意填表方向,一定是已知 => 未知
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = matrix[i][m - 1] + dp[i + 1][m - 1];
        }

        for (int j = m - 2; j >= 0; j--) {
            dp[n - 1][j] = matrix[n - 1][j] + dp[n - 1][j + 1];
        }
        // 从下到上,从右到左
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int right = matrix[i][j] + dp[i][j + 1];
                int down = matrix[i][j] + dp[i + 1][j];
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    // 压缩状态
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;

        int[] arr = new int[col];
        arr[0] = m[0][0];

        for (int j = 1; j < col; j++) {
            arr[j] = arr[j - 1] + m[0][j];
        }

        for (int i = 1; i < row; i++) {
            arr[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j]) + m[i][j];
            }
        }

        return arr[col - 1];
    }


}
