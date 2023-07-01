package alg_02_train_wyj._19_day_DFS_BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 16:15
 * @Version 1.0
 */
public class _07_733_flood_fill {

    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int rows = image.length;
        int cols = image[0].length;
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;
        dfs(image, dirs, sr, sc, rows, cols, newColor, oldColor);
        return image;
    }

    public void dfs(int[][] image, int[][] dirs, int i, int j,
                    int rows, int cols, int newColor, int oldColor) {

        if (i < 0 || i >= rows || j < 0 || j >= cols
                || image[i][j] != oldColor) {
            return;
        }

        image[i][j] = newColor;
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            dfs(image, dirs, nexti, nextj, rows, cols, newColor, oldColor);
        }
    }

    public int[][] floodFill11(int[][] image, int sr, int sc, int newColor) {

        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        int rows = image.length;
        int cols = image[0].length;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

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

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && image[nextRow][nextCol] == oldColor) {
                    queue.offer(new int[]{nextRow, nextCol});
                    image[nextRow][nextCol] = newColor;
                }
            }
        }
        return image;
    }
}
