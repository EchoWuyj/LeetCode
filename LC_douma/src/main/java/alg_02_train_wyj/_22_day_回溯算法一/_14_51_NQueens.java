package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 11:11
 * @Version 1.0
 */
public class _14_51_NQueens {
    private int n;
    private int[] rows;
    private int[] cols;
    private int[] mains;
    private int[] secondary;
    private List<List<String>> output;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.mains = new int[2 * n - 1];
        this.secondary = new int[2 * n - 1];
        this.output = new ArrayList<>();
        // 从 0 行开始，依次遍历每行
        dfs(0);
        return output;
    }

    private void dfs(int row) {
        if (row >= n) return;
        for (int col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row == n - 1) addSolution();
                dfs(row + 1);
                removeQueen(row, col);
            }
        }
    }

    private void placeQueen(int row, int col) {
        rows[row] = col;
        cols[col] = 1;
        mains[row - col + n - 1] = 1;
        secondary[row + col] = 1;
    }

    private void removeQueen(int row, int col) {
        rows[row] = 0;
        cols[col] = 0;
        mains[row - col + n - 1] = 0;
        secondary[row + col] = 0;
    }

    private boolean isNotUnderAttack(int row, int col) {
        int res = cols[col] + mains[row - col + n - 1] + secondary[row + col];
        return res == 0;
    }

    private void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int col = rows[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) sb.append(".");
            sb.append("Q");
            for (int j = col + 1; j < n; j++) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public static void main(String[] args) {
        System.out.println(new _14_51_NQueens().solveNQueens(4));
        // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}
