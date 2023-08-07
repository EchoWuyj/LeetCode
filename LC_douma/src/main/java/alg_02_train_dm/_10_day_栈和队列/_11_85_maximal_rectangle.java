package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 15:08
 * @Version 1.0
 */
public class _11_85_maximal_rectangle {

    /*
        85. 最大矩形
        给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
        找出只包含 1 的最大矩形，并返回其面积。

        示例 1：
        输入：matrix = [["1","0","1","0","0"],
                        ["1","0","1","1","1"],
                        ["1","1","1","1","1"],
                        ["1","0","0","1","0"]]
        输出：6
        解释：最大矩形如上图所示。

        提示：
        rows == matrix.length
        cols == matrix[0].length
        1 <= row, cols <= 200
        matrix[i][j] 为 '0' 或 '1'

     */

    // KeyPoint 思考方式
    // 遇到陌生的题目，没有什么思路，先暴力求解
    // => 时间复杂度太高 => 思考如何优化
    public int maximalRectangle(char[][] matrix) {

        // KeyPoint 加深对矩阵理解
        // 关于矩阵 matrix，其为 m × n 行列
        // [ arr1,
        //   arr2,
        //   ...
        //   arr3]

        // m：arri 个数 => m 个一维数组
        // n：arri.length => 每个一维数组长度为 n

        // KeyPoint 常规思路
        // 暴力求解：依次遍历矩阵每个位置，对矩阵每个位置计算
        // 且固定方向只向左，向上，计算只包含 '1' 的最大矩形面积
        // => 时间复杂度太高
        // => 思考别的方式

        // KeyPoint 思路：先对矩阵预处理
        // 计算任意位置 (row,col) 左边连续 1 的个数，包括当前位置的 1
        // 在此基础上，计算只包含 '1' 的最大矩形的面积

        // 示例 1 原始 matrix
        // 1 0 1 0 0
        // 1 0 1 1 1
        // 1 1 1 1 1
        // 1 0 0 1 0

        // 处理后 matrix
        // 1 0 1 0 0
        // 1 0 1 2 3
        // 1 2 3 4 5
        // 1 0 0 1 0

        // 行
        int m = matrix.length;
        if (m == 0) return 0;
        // 列
        int n = matrix[0].length;

        // 计算任意位置 (row,col) 左边连续 1 的个数，包括当前位置的 1
        // 明确创建二维矩阵的数据类型，int 类型
        int[][] counts = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 1.只考虑'1'情况
                // 2.使用二维矩阵时，明确二维矩阵中元素，是 int，还是 char，
                //   保证数据类型一致，否则 if 判断无法进行匹配
                if (matrix[i][j] == '1') {
                    // j == 0 表示第一列 =>  counts[i][j] = 1
                    // 否则，counts[i][j] = counts[i][j - 1] + 1
                    counts[i][j] = (j == 0 ? 0 : counts[i][j - 1]) + 1;
                }
            }
        }

        // 处理后 matrix
        // 1 0 1 0 0
        // 1 0 1 2 3
        // 1 2 3 4 5
        // 1 0 0 1 0
        //       ↑
        //       j

        // KeyPoint 问题转化
        // 从第一列到最后一列，求矩阵中每一列，柱状图中最大的矩形

        // 如：将 j 指针对应列，旋转过来，且按照行，从上往下的次序 0 2 4 1
        //         _
        //        |_|
        //     _  |_|
        //    |_| |_|  _
        //  _ |_| |_| |_|
        //  ↑          ↑
        //  0 行      m-1行

        int res = 0;
        for (int j = 0; j < n; j++) {

            // left 每根柱子的左边界 => 柱子左边第一个小于这根柱子的柱子
            // right 每根柱子的右边界 => 柱子右边第一个小于这根柱子的柱子
            int[] left = new int[m];
            int[] right = new int[m];
            Arrays.fill(right, m);

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            // 从 left 往 right 遍历 => 从第一行到最后一行
            for (int i = 0; i < m; i++) {
                int count = counts[i][j];
                // 注意：j 是固定的，即同一列，i 是循环变量，表示不同行
                while (!stack.isEmpty() && count <= counts[stack.peek()][j]) {
                    right[stack.peek()] = i;
                    stack.pop();
                }
                left[i] = (stack.isEmpty() ? -1 : stack.peek());
                stack.push(i);
            }

            for (int mid = 0; mid < m; mid++) {
                int height = counts[mid][mid];
                int width = right[mid] - left[mid] - 1;
                res = Math.max(res, height * width);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        // KeyPoint 注意事项
        // 创建二维数组时，明确二维数组中元素，是 int，还是 char
        // 若是 char，字符类型，得加 ''，字符串则使用 ""
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
    }
}
