package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 17:56
 * @Version 1.0
 */
public class _11_240_search_a_2d_matrix_ii {

    public boolean searchMatrix1(int[][] matrix, int target) {
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

    public static boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int times = Math.min(m, n);
        for (int i = 0; i < times; i++) {
            boolean isRowExist = BSRow(matrix, target, i, n);
            boolean isColExist = BSCol(matrix, target, i, m);
            if (isRowExist || isColExist) return true;
        }

        return false;
    }

    public static boolean BSRow(int[][] matrix, int target, int i, int n) {
        int left = i, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[i][mid]) {
                return true;
            } else if (target < matrix[i][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static boolean BSCol(int[][] matrix, int target, int i, int m) {
        int left = i, right = m - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("mid = " + mid);
            System.out.println("i = " + i);

            if (target == matrix[mid][i]) {
                return true;
            } else if (target < matrix[i][mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {{-1}, {-1}};
        searchMatrix2(arr, 0);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) j++;
            else if (matrix[i][j] > target) i--;
            else return true;
        }
        return false;
    }
}
