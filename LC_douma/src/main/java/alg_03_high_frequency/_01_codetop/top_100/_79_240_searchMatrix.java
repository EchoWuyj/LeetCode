package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:46
 * @Version 1.0
 */
public class _79_240_searchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int row = m - 1;
        int col = 0;

        // 只要索引不越界，一直执行 while 循环，不断搜索
        while (row >= 0 && col < n) {
            if (matrix[row][col] < target) col++;
            else if (matrix[row][col] > target) row--;
            else return true;
        }
        return false;
    }
}
