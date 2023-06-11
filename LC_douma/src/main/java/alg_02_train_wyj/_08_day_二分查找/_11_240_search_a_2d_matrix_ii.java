package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 17:56
 * @Version 1.0
 */
public class _11_240_search_a_2d_matrix_ii {

    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int shortDim = Math.min(m, n);
        for (int i = 0; i < shortDim; i++) {
            boolean rowFind = BSRow(matrix, i, target);
            boolean colFind = BSCol(matrix, i, target);

            if (rowFind || colFind) {
                return true;
            }
        }
        return false;
    }

    public boolean BSRow(int[][] matrix, int row, int target) {
        int low = row;
        int high = matrix[0].length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (target < matrix[row][mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public boolean BSCol(int[][] matrix, int col, int target) {
        int low = col;
        int high = matrix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][col] == target) {
                return true;
            } else if (target < matrix[mid][col]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int row = m - 1;
        int col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] < target) col++;
            else if (matrix[row][col] > target) row--;
            else return true;
        }
        return false;
    }
}
