package alg_02_train_dm._03_day_二维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 11:10
 * @Version 1.0
 */
public class _05_54_spiral_matrix2 {

    // KeyPoint 方法二 按层模拟
    // 思路：每次遍历一整行，或者一整列
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        // 定义矩阵长宽 m 和 n，不需要 -1
        int m = matrix.length;
        int n = matrix[0].length;

        //            ↓-----------↓
        // startRow → 1  2  3  4  5
        //          → 6  7  8  9  10 ←
        //          | 11 12 13 14 15 |
        //  endRow  → 16 17 18 19 20 ←
        //             ↑           ↑
        //          startCol    endCol
        //             ↑--------↑

        // KeyPoint 矩阵遍历中：方向和索引变换，一一对应
        // 1.往右，往下，都是 +1
        //   往上，往左，都是 -1
        // 2.上下 i 控制
        //   左右 j 控制

        // 指针定义
        // 开始行，结束行；
        // => startRow 和 endRow
        // 开始列，结束列
        // => startCol 和 endCol

        // 注意：指针 endRow，endCol 作为矩阵索引坐标需要 -1，避免索引越界
        int startRow = 0, endRow = m - 1;
        int startCol = 0, endCol = n - 1;

        // 只要 startRow > endRow 或者 startCol > endCol
        // => 说明已经没有元素需要遍历了，结束 while 循环
        while (startRow <= endRow && startCol <= endCol) {
            // 1.最上层行
            // 遍历行，startRow 不变化，更新 col 索引
            for (int col = startCol; col <= endCol; col++) res.add(matrix[startRow][col]);
            // 2.最右侧列
            // r 从 startRow+1 开始
            for (int row = startRow + 1; row <= endRow; row++) res.add(matrix[row][endCol]);

            // KeyPoint 矩阵中的边界条件：一行，一列

            // 1.一行情况
            //   startRow → endRow →  1  2  3  4
            //                        ↑        ↑
            //                     startCol  endCol

            //  startRow → endRow →   1  2  3  4
            //                                 ↑
            //                               endCol
            //                                 ↑
            //                              startCol

            // 2.一列情况
            //   startRow →  5
            //              10
            //              15
            //    endRow →  20
            //              ↑
            //          startCol
            //           endCol

            //                      5
            //                      10
            //                      15
            // startRow → endRow →  20
            //                       ↑
            //                   startCol
            //                    endCol

            // KeyPoint 在做索引变换时，一定得考虑索引越界
            // 不判断，存在索引越界
            if (startRow < endRow && startCol < endCol) {
                // 3.最下层行
                // 注意：从右往左遍历，for 循环中变量是自减，不是自增
                for (int col = endCol - 1; col >= startCol; col--) res.add(matrix[endRow][col]);
                // 4.最左侧列
                // 注意：startRow 的下一行，startRow+1，而不是 startRow-1
                for (int row = endRow - 1; row >= startRow + 1; row--) res.add(matrix[row][startCol]);
            }

            // 一轮 for 循环结束，更新索引
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }
        return res;
    }
}
