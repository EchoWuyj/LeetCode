package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 14:35
 * @Version 1.0
 */
public class _02_48_rotate_image1 {
    /*
        48. 旋转图像
        给定一个 n × n 的二维矩阵 matrix 表示一个图像。
        请你将图像顺时针旋转 90 度。
        你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。
        请不要 使用另一个矩阵来旋转图像。

        提示：
        n == matrix.length == matrix[i].length
        1 <= n <= 20
        -1000 <= matrix[i][j] <= 1000

        KeyPoint 分析：直接模拟 => 本质找规律

        顺时针旋转 90 度 => 水平边 -> 竖直边
         _____
         1 2 3    7 4 1|
         4 5 6 => 8 5 2|
         7 8 9    9 6 3|

        规律：关键分析'行'和'列'变换关系
        1.原矩阵第 n 行 -> 旋转矩阵倒数 n 列
        2.原矩阵第 n 列 -> 旋转矩阵第 n 行

        => 对于矩阵中第 row 行，第 col 列元素
           旋转矩阵中 倒数 第 row 列，第 col 行位置

        => 行列坐标关系
           matrix[row][col] => matrix[col[[n-row-1]
           (抽象公式可以使用具体数字验证是否正确)

     */

    // KeyPoint 方法一 申请额外数组 => 不符合题目要求
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(n^2)
    public void rotate1(int[][] matrix) {
        // n 为长度，实际索引需要减 1，否则越界
        int n = matrix.length;
        // 申请额外数组
        int[][] tmpMatrix = new int[n][n];

        // tmpMatrix 存储旋转后数组
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 通过找规律，发现下标索引之间关系
                tmpMatrix[col][n - row - 1] = matrix[row][col];
            }
        }

        // tmpMatrix 赋值原来数组  matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = tmpMatrix[row][col];
            }
        }
    }
}

