package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:20
 * @Version 1.0
 */
public class _11_85_maximal_rectangle {

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] left = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1') {
                    left[row][col] = (col == 0 ? 0 : left[row][col - 1]) + 1;
                }
            }
        }
        int ans = 0;
        for (int col = 0; col < n; col++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Arrays.fill(down, m);
            ArrayDeque<Integer> stack = new ArrayDeque<>();
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
}
