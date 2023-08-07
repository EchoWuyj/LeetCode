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

        int[][] counts = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    counts[i][j] = (j == 0 ? 0 : counts[i][j - 1]) + 1;
                }
            }
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            int[] left = new int[m];
            int[] right = new int[m];
            Arrays.fill(right, m);
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                int count = counts[i][j];
                while (!stack.isEmpty() && count <= counts[stack.peek()][j]) {
                    right[stack.pop()] = i;
                }
                left[i] = (stack.isEmpty() ? -1 : stack.peek());
                stack.push(i);
            }
            for (int mid = 0; mid < m; mid++) {
                int height = counts[mid][j];
                int width = right[mid] - left[mid] - 1;
                res = Math.max(res, height * width);
            }
        }
        return res;
    }
}
