package alg_02_train_dm._08_day_二分查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 16:35
 * @Version 1.0
 */

//
public class _12_240_search_a_2d_matrix_ii1 {
    
    /*
        240. 搜索二维矩阵 II
        编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target
        该矩阵具有以下特性：
           1 每行的元素从左到右升序排列。
           2 每列的元素从上到下升序排列。

        KeyPoint 一般在相对有序数组中，判断是否存在某个元素 target，可以使用二分查找

        示例 1：
        输入：matrix = [[1,4,7,11,15],
                       [2,5,8,12,19],
                       [3,6,9,16,22],
                       [10,13,14,17,24],
                       [18,21,23,26,30]],
                       target = 5
        输出：true

        示例 2：
        输入：matrix = [[1,4,7,11,15],
                        [2,5,8,12,19],
                        [3,6,9,16,22],
                        [10,13,14,17,24],
                        [18,21,23,26,30]],
                        target = 20
        输出：false

        提示：
        m == matrix.length
        n == matrix[i].length
        1 <= n, m <= 300
        -109<= matrix[i][j] <= 109
        每行的所有元素从左到右升序排列
        每列的所有元素从上到下升序排列
        -109<= target <= 109


     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度 O(m*n)
    // 空间复杂度 O(1)
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        // 二维数组中没有元素
        if (m == 0) return false;
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

    // KeyPoint 方法二 二分查找
    // 利用矩阵行和列是有序数组，对矩阵的行和列二分查找
    // 时间复杂度 O(min{m,n}*(logn + logm))
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // 每次从主对角线位置开始，先对行进行二分查找，在对列二分查找
        // 查找次数取决于：主对角线长度，也就是行和列中较小的维度，即 min(m, n)
        int shortDim = Math.min(m, n);

        // 从主对角线位置 (0,0)位置开始，依次循环遍历 shortDim 次
        for (int i = 0; i < shortDim; i++) {
            // 通过对角线的位置，确定 row 和 col
            // KeyPoint 传入形参和函数签名形式保持一致
            boolean rowFind = binarySearchRow(matrix, target, i, n); // logn
            boolean colFind = binarySearchCol(matrix, target, i, m); // logm
            // 行或列找到，返回 true
            if (rowFind || colFind) {
                return true;
            }
        }
        return false;
    }

    // 在行上二分查找
    private boolean binarySearchRow(int[][] matrix, int target, int i, int n) {
        int left = i;
        // 列信息(横向元素个数)，不要和 matrix.length 搞反了
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // if 进行数值大小的比较，使用 matrix[i][mid]，而不是使用 mid 索引
            // 在 行 上二分查找
            // 1.行 row 固定 => 通过对角线位置确定行 i
            // 2.列 col 变换 => 通过计算得确定列 mid
            if (matrix[i][mid] == target) {
                return true;
            } else if (matrix[i][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 在列上二分查找
    private boolean binarySearchCol(int[][] matrix, int target, int i, int m) {
        int left = i;
        // 行信息(纵向元素个数)，不要和 matrix[0].length 搞反了
        int right = m - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // KeyPoint 特别注意
            // 在 列 上二分查找
            // 1.行 row 变换 => 通过计算得确定列 mid
            // 2.列 col 固定 => 通过对角线位置确定行 i
            // => mid 在前，i 在后，否则：索引越界
            // => matrix[mid][i]，区别于：行的 matrix[i][mid]
            if (matrix[mid][i] == target) {
                return true;
            } else if (matrix[mid][i] < target) {
                // 注意：修改时 matrix[i][mid] => matrix[i][mid]，else if 判断条件需要同时修改
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }


}
