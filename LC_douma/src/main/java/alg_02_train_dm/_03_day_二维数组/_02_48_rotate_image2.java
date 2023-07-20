package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 20:38
 * @Version 1.0
 */
public class _02_48_rotate_image2 {

    // KeyPoint 方法二 原地'旋转'
    public void rotate2(int[][] matrix) {

        // KeyPoint 确定 for 循环次数
        // 矩阵一次旋转调整 4 个元素
        // 1.若 n 为偶数，n^2/4 => (n/2)*(n/2)，分别控制行和列
        // 2.若 n 为奇数，中心位置元素不动，(n^2-1)/4 => [(n-1)/2]*[(n+1)/2]

        // 统一奇偶两种形式
        // 行循环次数：当 n=4，n/2=2；当 n=5，(n-1)/2=2，效果等价于 n/2  => 故使用：n/2
        // 列循环次数：当 n=4，n/2=2，效果等价于 n+1/2 ；当 n=5，(n+1)/2=3 => 故使用：(n+1)/2

        // KeyPoint 旋转替换
        // 1.索引计算，每个元素都满足：[row][col] -> [col[[n-row-1]
        // 2.将旋转一周的每个位置上都进行公式置换

        // 临时变量 tmp，先将 [row][col] 位置元素记录，可以旋转的前一个元素覆盖
        // 如：先记录 A = tmp，A 已经是自由的，可以被覆盖，B -> A
        // 同理，D -> B，C -> D，tmp -> C，从而实现元素旋转一周
        //      A
        //   B     C
        //      D

        int n = matrix.length;
        for (int row = 0; row < n / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                int tmp = matrix[row][col];
                // KeyPoint 注意：赋值等号'='，左右两边元素的前后位置
                // => 被赋值 = 赋值
                // (row,col) <= 逆时针旋转 90° 位置元素覆盖
                // 旋转矩阵元素 <= 原矩阵元素
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = tmp;
            }
        }
    }
}
