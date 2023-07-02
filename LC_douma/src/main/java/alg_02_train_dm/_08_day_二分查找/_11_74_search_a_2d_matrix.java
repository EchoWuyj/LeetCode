package alg_02_train_dm._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 19:50
 * @Version 1.0
 */
public class _11_74_search_a_2d_matrix {

    /*
        74. 搜索二维矩阵
         编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
         该矩阵具有如下特性：
         1.每行中的整数 从左到右 按升序排列。
         2.每行的第一个整数大于前一行的最后一个整数。

         示例 1：
         输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
         输出：true

         提示：
         m == matrix.length
         n == matrix[i].length
         1 <= m, n <= 100
        -104 <= matrix[i][j], target <= 1

     */

    // KeyPoint 方法一 暴力搜索
    // 时间复杂度 O(m*n) => 相当于遍历矩阵中每个位置
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == target) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 二分查找
    // 思路：将有序的二维数组转成有序的一维数组，再使用二分查找
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 二维数组元素个数：m*n，对应一维数组大小：m*n-1
        int left = 0, right = m * n - 1;
        while (left <= right) {
            // 1.二分使用一维索引 => mid
            int mid = left + (right - left) / 2;
            // 2.比较值 => 一维 mid 索引 -> 二维数组 [row][col] 索引
            //   n 列数，列数可以认为'一组'，一个列数(n)凑成一组
            //   row：/ 确定在那一组
            //   col：% 余确定在一组中的位置
            //   => matrix[row][col] 值
            int num = matrix[mid / n][mid % n];
            if (num == target) return true;
            else if (num < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
