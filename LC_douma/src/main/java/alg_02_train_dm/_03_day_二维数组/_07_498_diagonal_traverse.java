package alg_02_train_dm._03_day_二维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 17:11
 * @Version 1.0
 */
public class _07_498_diagonal_traverse {

    /*
        498. 对角线遍历
        给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素

        提示：
        m == mat.length
        n == mat[i].length
        1 <= m, n <= 10^4
        1 <= m * n <= 10^4
        -10^5 <= mat[i][j] <= 10^5

        1 2 3
        4 5 6
        7 8 9

        对角线遍历：1,2,4,7,5,3,6,8,9
        遍历顺序：先斜上，再斜下，依次反复

        KeyPoint 分析
        斜上：row -1，col +1
        斜下：row +1，col -1

        row 或者 col 越界，如何处理
        斜上过程，若 row < 0：row = 0，col 不变，换方向
        斜下过程，若 col < 0：row 不变，col = 0，换方向
        斜下过程，若 row >= m：row = m-1，col += 2，换方向
        斜上过程，若 col >= n：row += 2，col= n-1，换方向

        以上只是考虑，row 或者 col 越界，没有考虑了 (row,col) 同时越界
        斜上过程，(row,col) 同时越界，(row < 0 ,col >= n)
            执行 col >= n：row += 2，col= n-1，就已经不越界
            不需要再执行 row < 0：row = 0，col 不变

        斜下过程，(row,col) 同时越界，(row >= m,col < 0)
            执行 row >= m：row = m-1，col += 2，就已经不越界
            不需要再执行 col < 0：row 不变，col = 0

        故执行代码顺序是有要求的
            若 col >= n：row += 2，col= n-1，换方向
            若 row >= m：row = m-1，col += 2，换方向
            若 col < 0：row 不变，col = 0，换方向
            若 row < 0：row = 0，col 不变，换方向

     */

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // 先斜上，再斜下
        int[][] dirs = {{-1, 1}, {1, -1}};

        int row = 0, col = 0, di = 0;
        int[] res = new int[m * n];
        // 遍历每个元素
        for (int i = 0; i < m * n; i++) {
            res[i] = mat[row][col];

            // 计算下个位置 (row,col) 坐标
            row = row + dirs[di][0];
            col = col + dirs[di][1];

            // 边界情况处理，4 种情况不同处理逻辑
            if (col >= n) {
                col = n - 1;
                row += 2;
                // 换方向
                di = 1 - di;
            }

            if (row >= m) {
                row = m - 1;
                col += 2;
                di = 1 - di;
            }

            if (row < 0) {
                row = 0;
                di = 1 - di;
            }

            if (col < 0) {
                col = 0;
                di = 1 - di;
            }
            // 没有遇到边界情况，正常斜上或者斜下操作，不会触发换方向操作
        }
        return res;
    }
}
