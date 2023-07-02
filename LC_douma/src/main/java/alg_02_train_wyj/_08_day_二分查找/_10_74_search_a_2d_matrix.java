package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 17:41
 * @Version 1.0
 */
public class _10_74_search_a_2d_matrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[mid / n][mid % n]) {
                return true;
            } else if (matrix[mid / n][mid % n] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
