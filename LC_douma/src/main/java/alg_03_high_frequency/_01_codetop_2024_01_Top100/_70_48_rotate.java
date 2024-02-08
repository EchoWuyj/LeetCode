package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:40
 * @Version 1.0
 */
public class _70_48_rotate {

    // 旋转图像
    // 数学公式
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // i => n/2
        // j => (n+1)/2
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 当前 matrix[i][j] 作为临时 tmp
                int tmp = matrix[i][j];
                // 从 matrix[i][j] 开始，首尾相连，推测后续坐标，最终以 tmp 首尾
                // 规律：外坐标相同，内坐标和为 n-1
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }
}
