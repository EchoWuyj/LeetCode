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
    private int[] main;
    private int[] secondary;
    private List<List<String>> output;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
        main = new int[2 * n - 1];
        secondary = new int[2 * n - 1];
        output = new ArrayList<>();
        dfs(0);
        return output;
    }

    public void dfs(int row) {
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
        main[row - col + n - 1] = 1;
        secondary[row + col] = 1;
    }

    private void removeQueen(int row, int col) {
        rows[row] = 0;
        cols[col] = 0;
        main[row - col + n - 1] = 0;
        secondary[row + col] = 0;
    }

    private boolean isNotUnderAttack(int row, int col) {
        int res = cols[col] + main[row - col + n - 1] + secondary[row + col];
        return res == 0;
    }

    private void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int col = rows[i];
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < col; j++) builder.append(".");
            builder.append("Q");
            for (int j = col + 1; j < n; j++) builder.append(".");
            solution.add(builder.toString());
        }
        output.add(solution);
    }
}
