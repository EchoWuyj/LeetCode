package alg_02_train_wyj._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 20:34
 * @Version 1.0
 */
public class _09_119_pascals_triangle_ii {
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            List<Integer> curRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curRow.add(1);
                } else {
                    List<Integer> preRow = res.get(row - 1);
                    curRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            res.add(curRow);
        }
        return res.get(rowIndex);
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> preRow = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            List<Integer> curRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curRow.add(1);
                } else {
                    curRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            preRow = curRow;
        }
        return preRow;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            curRow.add(0);
            for (int col = row; col > 0; col--) {
                curRow.set(col, curRow.get(col - 1) + curRow.get(col));
            }
        }
        return curRow;
    }
}
