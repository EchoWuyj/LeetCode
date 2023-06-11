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

    // 思考方式 => 没有什么思路，先暴力求解 => 时间复杂度太高 => 思考如何优化
    public int maximalRectangle(char[][] matrix) {
        // m × n 行列
        // [ arr1,
        //   arr2,
        //   ...
        //   arr3]
        // arri 个数 => m
        // arri.length => n

        // 行
        int m = matrix.length;
        if (m == 0) return 0;
        // 列
        int n = matrix[0].length;

        // 计算 任意位置 (row,col) 左边连续 1 的个数 => 包括当前位置的 1
        int[][] left = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 只考虑 1 的情况
                // 创建二维数组时，明确二维数组中元素，是 int，还是 char
                // 否则数据类型不一致，无法 if 判断无法进行匹配
                if (matrix[row][col] == '1') {
                    left[row][col] = (col == 0 ? 0 : left[row][col - 1]) + 1;
                }
            }
        }

        // 示例 1 数据
        //[[1, 0, 1, 0, 0],
        // [1, 0, 1, 2, 3],
        // [1, 2, 3, 4, 5],
        // [1, 0, 0, 1, 0]]

        int ans = 0;
        // 问题 => 求矩阵中每一列柱状图中最大的矩形
        // 从第一列到最后一列
        for (int col = 0; col < n; col++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Arrays.fill(down, m);

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            // 从 up 往 down 遍历 => 从第一行到最后一行
            // col 是固定的，即同一列，不同行
            for (int row = 0; row < m; row++) {
                int x = left[row][col];
                while (!stack.isEmpty() && x <= left[stack.peek()][col]) {
                    down[stack.peek()] = row;
                    stack.pop();
                }
                up[row] = (stack.isEmpty() ? -1 : stack.peek());
                stack.push(row);
            }

            for (int row = 0; row < m; row++) {
                int height = left[row][col];
                int width = down[row] - up[row] - 1;
                ans = Math.max(ans, height * width);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 创建二维数组时，明确二维数组中元素，是 int，还是 char
        // 否则数据类型不一致，无法 if 判断无法进行匹配
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] left = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // 只考虑 1 的情况
                if (matrix[row][col] == '1') {
                    left[row][col] = (col == 0 ? 0 : left[row][col - 1]) + 1;
                }
            }
        }
        //
        System.out.println(Arrays.deepToString(left));
    }
}
