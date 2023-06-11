package alg_02_train_dm._19_day_DFS_BFS;

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
        `有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
        给你一个坐标 (sr, sc) 表示图像渲染开始的像素值(行，列)和一个新的颜色值 newColor，
        让你重新上色这幅图像。

        为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
        接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，
        重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。最后返回经过上色渲染后的图像。`



        输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
        输出: [[2,2,2],[2,2,0],[2,0,1]]
        解析: 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
        注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。

     */

    // 定义全局变量，提供给两个方法使用
    private int[][] image;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows;
    private int cols;

    private int oldColor;

    // KeyPoint 方法一 DFS
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        // 若 oldColor 和 newColor 颜色一样，直接返回
        if (oldColor == newColor) {
            return image;
        }
        this.image = image;
        this.rows = image.length;
        this.cols = image[0].length;

        dfs(sr, sc, newColor);
        return image;
    }

    private void dfs(int row, int col, int newColor) {
        // 递归边界 row col 越界 或者 [row][col] != oldColor，返回上层
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return;
        }

        // 若 [row][col] 设置为 newColor，说明 [row][col] 已经被访问过了
        image[row][col] = newColor;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            // 深度优先遍历，将 newColor 进行填充
            dfs(nextRow, nextCol, newColor);
        }
    }

    // KeyPoint 方法二 BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = image.length;
        int cols = image[0].length;

        // 使用 ArrayDeque 实现 Queue 接口
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        // 加入队列后，需要标记成已染色
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        // [nextRow][nextCol] == oldColor 该节点没有访问过
                        && image[nextRow][nextCol] == oldColor) {
                    queue.offer(new int[]{nextRow, nextCol});
                    // 加入队列后，需要标记成已染色
                    image[nextRow][nextCol] = newColor;
                }
            }
        }
        return image;
    }
}
