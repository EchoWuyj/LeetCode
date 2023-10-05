package alg_02_train_dm._08_day_二分查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-02 20:52
 * @Version 1.0
 */
public class _12_240_search_a_2d_matrix_ii2_推荐{

    // KeyPoint 方法三 缩减搜索空间 => 推荐
    // 利用矩阵行列 左下角 位置特点，即'往右递增，往上递减'的特点，来快速找到 target
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // 以 左下角 (m-1,0) 为起始位置
        int row = m - 1;
        int col = 0;
        // 只要在矩阵范围内，则一直进行搜索
        // 在 向右移 或者 向上移 的过程中，避免 row 和 col 越界
        // while 执行的条件
        while (row >= 0 && col < n) {
            // target > [row][co] => 向右移
            if (matrix[row][col] < target) col++;
                // target < [row][co] => 向上移
            else if (matrix[row][col] > target) row--;
                // 直到 matrix[row][col] == target，则返回 true
            else return true;
        }
        return false;
    }
}
