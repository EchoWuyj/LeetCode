package alg_02_train_dm._19_day_DFS_BFS_二刷;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 16:53
 * @Version 1.0
 */
public class _07_733_flood_fill {
    /*
        733. 图像渲染
        有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
        给你一个坐标 (sr, sc) 表示图像渲染开始的像素值(行，列)和一个新的颜色值 newColor，
        让你重新上色这幅图像。

        为了完成上色工作，从初始坐标开始，记录初始坐标的 上下左右 四个方向上像素值与初始坐标相同的相连像素点，
        接着再记录这四个方向上符合条件的像素点 与他们对应四个方向上像素值与初始坐标相同的相连像素点，
        重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。最后返回经过上色渲染后的图像。

        1 1 1       2 2 2
        1 1 0  =>   2 2 0
        1 0 1       2 0 1

        输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
        输出: [[2,2,2],[2,2,0],[2,0,1]]
        解析: 在图像的正中间，坐标(sr,sc)=(1,1),在路径上所有符合条件的像素点的颜色都被更改成2。
        注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。

        提示:
        m == image.length
        n == image[i].length
        1 <= m, n <= 50
        0 <= image[i][j], newColor < 216
        0 <= sr <m
        0 <= sc <n


     */

    // 定义全局变量，提供给两个方法使用
    private int[][] image;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows;
    private int cols;
    private int oldColor;
//    private boolean[][] visited;

    // KeyPoint 方法一 DFS
    // 充血(染色) 算法
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        // 若 oldColor 和 newColor 颜色一样，直接返回
        if (oldColor == newColor) return image;
        this.image = image;
        this.rows = image.length;
        this.cols = image[0].length;
//        this.visited = new boolean[rows][cols];

        dfs(sr, sc, newColor);
        return image;
    }

    // dfs 遍历中，只是修改颜色，不需要返回值，故返回值为 void
    private void dfs(int row, int col, int newColor) {

        // 整体结构：类似多叉树遍历(二叉树遍历)
        // 1.递归边界
        // 2.当前节点操作
        // 3.相邻节点 dfs

        // 递归边界
        // 递归条件前置 => row col 越界 或者 [row][col] != oldColor，返回上层
        // 类似于：04_79_word_search 中的 dfs1 方法
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return;
        }

//        if (row < 0 || row >= rows || col < 0 || col >= cols
//                || image[row][col] != oldColor || visited[row][col]) {
//            return;
//        }

        // 若 [row][col] 设置为 newColor，说明 [row][col] 已经被访问过了
        image[row][col] = newColor;

        // visited[row][col] = true;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 深度优先遍历，将 newColor 进行填充
            dfs(nextRow, nextCol, newColor);

            // if (!visited[nextRow][nextCol]) dfs(nextRow, nextCol, newColor);
            // 存在 bug，因为 nextRow 或者 nextCol 可能存在索引越界
            // => 同一递归判断位置，要么都前置，要么都后置，不要一个前置，一个后置
            // => 将 visited[row][col] 前置，放到递归边界那里，且使用 row 和 col

            // 图的遍历，保证一个顶点只能访问一次，避免走回头路
            // => 因为染色的缘故，导致 newColor 和 oldColor 不一样，等价于标记已经访问过了

            // 注意：本题不需要回溯，目的就是渲染颜色，只要和其相邻就渲染即可
        }
    }

    // KeyPoint 方法二 BFS => 不是很熟悉
    // 原点 v 相邻的顶点 w(多个) 为一层，相邻顶点 w(多个) 的相邻顶点 z(多个) 为二层，以此类推
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int rows = image.length;
        int cols = image[0].length;

        // 使用 ArrayDeque 实现 Queue 接口，记忆：DQ
        // 队列中存储 顶点(row,col)
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        // 加入队列后，需要标记成已染色
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            // 相邻顶点
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                // 放入队列之前，需要判断一下，在范围之内，且没有访问过
                // KeyPoint 下边界 0 是可以取等的，上边界 rows 或 cols 不可以取等 => 经常犯的错误
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        // [nextRow][nextCol] == oldColor 表示：该节点没有访问过
                        && image[nextRow][nextCol] == oldColor) {
                    queue.offer(new int[]{nextRow, nextCol});
                    // 加入队列后，需要标记成已染色
                    image[nextRow][nextCol] = newColor;
                }
            }
        }
        return image;

        // 调试 bug，通过 输入，输出， 预期结果 三者之间的差异，分析 bug 可能的位置，而不是盲目找 bug
        // => dfs 边界判断有问题，导致没法执行 dfs => 下边界 0 是忘记取等了

        // 输入
        // [[1,1,1],
        // [1,1,0],
        // [1,0,1]]
        // 1
        // 1
        // 2

        // 输出
        //[[1,1,1],
        // [1,2,0],
        // [1,0,1]]

        // 预期结果
        //[[2,2,2],
        // [2,2,0],
        // [2,0,1]]
    }
}
