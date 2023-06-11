package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-18 14:35
 * @Version 1.0
 */
public class _02_48_rotate_image {
    /*
        48. 旋转图像
        给定一个 n × n 的二维矩阵 matrix 表示一个图像。
        请你将图像顺时针旋转 90 度。
        你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。
        请不要 使用另一个矩阵来旋转图像。
                   _____
        | 1 2 3    7 4 1
        | 4 5 6 => 8 5 2
        | 7 8 9    9 6 3

        顺时针旋转 90 度 => 竖直边 -> 水平边

        KeyPoint 直接模拟 => 本质找规律
        规律：关键分析'行'和'列'变换关系
        1 原矩阵第 n 行 -> 旋转矩阵最后 n 列
        2 原矩阵第 n 列 -> 旋转矩阵第 n 行

        => 对于矩阵中第 row 行，第 col 列元素，
           旋转矩阵中倒数第 row 列，第 col 行位置

        => matrix[row][col]
           matrix[col[[n-row-1]
          (抽象公式可以使用具体数字验证是否正确)

        提示：
        n == matrix.length == matrix[i].length
        1 <= n <= 20
        -1000 <= matrix[i][j] <= 1000

     */

    // KeyPoint 方法一 申请额外数组 => 不符合题目要求
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(n^2)
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        // 申请额外数组
        int[][] newMatrix = new int[n][n];

        // newMatrix 存储旋转后数组
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                newMatrix[col][n - row - 1] = matrix[row][col];
            }
        }

        // newMatrix 赋值原来数组  matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = newMatrix[row][col];
            }
        }
    }

    // KeyPoint 方法二 原地'旋转'
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 矩阵一次旋转调整 4 个元素，
        // 若 n 为偶数，n^2/4 => (n/2)*(n/2)，分别控制行和列
        // 若 n 为奇数，中心位置元素不动，(n^2-1)/4 => [(n-1)/2]*[(n+1)/2]
        // 行：当 n=4，n/2=2，当 n=5，(n-1)/2=2
        // 列：当 n=4，n+1/2=2，当 n=5，(n+1)/2=3
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                // 临时变量，先将 [row][col] 位置元素记录，可以旋转的前一个元素覆盖
                // 如：先记录 A = tmp，A 已经是自由的，可以被覆盖，B -> A
                // 同理，D -> B，C -> D，tmp -> C，从而实现元素旋转一周
                //    A
                // B     C
                //    D
                int tmp = matrix[row][col];
                // 索引计算，每个元素都满足 [row][col] -> [col[[n-row-1]
                // 将旋转一周的每个位置上都进行公式置换
                // KeyPoint 注意赋值等号'='两侧左右两边元素前后位置，原矩阵元素 => 旋转矩阵元素
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = tmp;
            }
        }
    }

    // KeyPoint 方法三 原地'翻转'
    // 根据索引坐标关系，即 [row][col] 和 [col][n-row-1]，推理出矩阵变换操作
    // matrix[row][col] -> 水平翻转 -> matrix[n-row-1][col] -> 主对角线翻转 -> matrix[col][n-row-1]
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // KeyPoint 水平翻转，遍历 n/2 行
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < n; col++) {
                // [row][col] 被记录之后，就可以被覆盖了
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = tmp;
            }
        }
        // 因为是 n*n 矩阵，具有对称性，直接交换即可
        // 主对角线翻转
        for (int row = 0; row < n; row++) {
            // KeyPoint bug 修复：到 row 为止即可，要不然就翻转回来了
            for (int col = 0; col < row; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }
}

