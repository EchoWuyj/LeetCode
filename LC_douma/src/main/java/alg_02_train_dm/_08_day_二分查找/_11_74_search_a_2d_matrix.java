package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 19:50
 * @Version 1.0
 */
// 74. 搜索二维矩阵
public class _11_74_search_a_2d_matrix {

    /*
        编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
        该矩阵具有如下特性：
          1 每行中的整数从左到右按升序排列。
          2 每行的第一个整数大于前一行的最后一个整数。
     */

    // KeyPoint 方法一 暴力搜索
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == target) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 二分查找
    // 将有序的二维数组转成有序的一维数组，再使用二分查找
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 一维 mid 索引 映射 二维数组 [row][col] 索引
            // n 列数，列数可以认为'一组'，一个列数(n)凑成一组
            // / 确定在那一组
            // % 余确定在一组中的位置
            int num = matrix[mid / n][mid % n];
            if (num == target) return true;
            else if (num < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
