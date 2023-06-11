package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:35
 * @Version 1.0
 */

// 240. 搜索二维矩阵 II
public class _12_240_search_a_2d_matrix_ii {
    
    /*
        编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target
        该矩阵具有以下特性：
           1 每行的元素从左到右升序排列。
           2 每列的元素从上到下升序排列。
        KeyPoint 一般在相对有序数组中，判断是否存在某个元素 target，可以使用二分查找
     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度 O(mn)
    // 空间复杂度 O(1)
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false; // 二维数组中没有元素
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

    // KeyPoint 方法二 二分查找
    // 利用矩阵行和列是有序数组，对行和列二分查找
    // 时间复杂度 O(min{m,n}*(logn + logm))
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int shortDim = Math.min(m, n);
        // 从 主对角线 依次循环遍历 shortDim 次
        for (int i = 0; i < shortDim; i++) { // min{m,n}
            // 通过对角线的位置，确定 row 和 col
            boolean rowFind = binarySearchRow(matrix, i, target); // logn
            boolean colFind = binarySearchCol(matrix, i, target); // logm
            // 行或列找到，返回 true
            if (rowFind || colFind) {
                return true;
            }
        }
        return false;
    }

    // 在行上二分查找
    private boolean binarySearchRow(int[][] matrix,
                                    int row, int target) {
        int low = row;
        // KeyPoint 列信息(横向元素个数)，不要和 matrix.length 搞反了
        int high = matrix[0].length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // KeyPoint if 判断使用 matrix[row][mid]，而不是使用 mid 索引进行判断
            //          通过对角线位置确定行 row，通过计算得确定列 mid
            //          记得 else if 判断条件需要同时修改
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    // 在列上二分查找
    private boolean binarySearchCol(int[][] matrix,
                                    int col, int target) {
        int low = col;
        // KeyPoint 行信息(纵向元素个数)，不要和 matrix[0].length 搞反了
        int high = matrix.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // KeyPoint mid 在前，col 在后
            if (matrix[mid][col] == target) {
                return true;
            } else if (matrix[mid][col] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    // KeyPoint 方法三 缩减搜索空间 => 推荐
    // 利用矩阵行列左下角位置特点，即'往右递增，往上递减'的特点，来快速找到 target
    public boolean searchMatrix(int[][] matrix,
                                int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int row = m - 1;
        int col = 0;
        // 避免 row 和 col 越界
        while (row >= 0 && col < n) {
            // 向右移
            if (matrix[row][col] < target) col++;
                // 向上移
            else if (matrix[row][col] > target) row--;
            else return true;
        }

        return false;
    }
}
