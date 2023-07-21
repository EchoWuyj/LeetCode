package alg_02_train_dm._03_day_二维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 17:11
 * @Version 1.0
 */
public class _07_498_diagonal_traverse {

    /*
        498. 对角线遍历
        给你一个大小为 m x n 的矩阵 mat
        请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素

        提示：
        m == mat.length
        n == mat[i].length
        1 <= m, n <= 10^4
        1 <= m * n <= 10^4
        -10^5 <= mat[i][j] <= 10^5


        示例 1：
                ↙
            1 2 3  ↙
         ↗ 4 5 6
            7 8 9
         ↗

        对角线遍历：1,|2,4,|7,5,3,|6,8,|9
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

    // 直接模拟
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // 定义方向 dirs
        // => 先斜上，再斜下
        // 斜上
        // => 分别分析，x 和 y 怎么变化 => x 上下， y 左右
        // => 最保险，通过实例，(1,0) => (0,1)，分析'斜上'坐标变换 (-1,1)
        int[][] dirs = {{-1, 1}, {1, -1}};

        int row = 0, col = 0, dir = 0;
        int[] res = new int[m * n];
        // 遍历每个元素
        for (int i = 0; i < m * n; i++) {
            res[i] = mat[row][col];

            // 计算下个位置 (row,col) 坐标
            // KeyPoint 特别注意
            // 下个位置 (row,col) 真实计算出来，并且已经更新到该位置，索引是可能存在越界
            // => 注意： (row,col) 不是越界前，索引坐标位置
            // 故需要根据 if 判断，判断是那种越界情况，并将索引设置成正确位置索引
            row = row + dirs[dir][0];
            col = col + dirs[dir][1];

            // 边界情况 => 4 种情况不同处理逻辑

            // KeyPoint 先后判断逻辑
            // 1.先判断 col >= n 和 row >= m
            // 2.再去判断 row < 0 和 col < 0

            // 注意：n 和 m 是可以取等的
            if (col >= n) {
                col = n - 1;
                row += 2;
                // 换方向
                // 因为 dirs 只是涉及两个方向，故使用这种方式调整方向
                dir = 1 - dir;
            }

            if (row >= m) {
                row = m - 1;
                col += 2;
                dir = 1 - dir;
            }

            if (row < 0) {
                row = 0;
                dir = 1 - dir;
            }

            if (col < 0) {
                col = 0;
                dir = 1 - dir;
            }

            // 没有遇到边界情况，正常斜上或者斜下操作，不会触发换方向操作
        }
        return res;
    }
}
