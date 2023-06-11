package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 16:15
 * @Version 1.0
 */
public class _07_733_flood_fill {

    int[][] image;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows;
    int cols;
    int oldColor;

    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        this.image = image;
        rows = image.length;
        cols = image[0].length;
        oldColor = image[sr][sc];

        dfs(sr, sc, newColor);
        return image;
    }

    public void dfs(int row, int col, int newColor) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return;
        }
        image[row][col] = newColor;
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol, newColor);
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        int oldColor = image[sr][sc];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int rows = image.length;
        int cols = image[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                if (nextRow >= 0 && nextRow < rows
                        && nextCol >= 0 && nextCol < cols
                        && image[nextRow][nextCol] == oldColor) {
                    queue.offer(new int[]{nextRow, nextCol});
                    image[nextRow][nextCol] = newColor;
                }
            }
        }
        return image;
    }
}
