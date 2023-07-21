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
            List<Integer> curList = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curList.add(1);
                } else {
                    List<Integer> preList = res.get(row - 1);
                    int curNum = preList.get(col) + preList.get(col - 1);
                    curList.add(curNum);
                }
            }
            res.add(curList);
        }
        return res.get(rowIndex);
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> preList = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            List<Integer> curList = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curList.add(1);
                } else {
                    int curNum = preList.get(col) + preList.get(col - 1);
                    curList.add(curNum);
                }
            }
            preList = curList;
        }
        return preList;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> curList = new ArrayList<>();
        curList.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            curList.add(0);
            for (int col = row; col > 0; col--) {
                int curNum = curList.get(col - 1) + curList.get(col);
                curList.set(col, curNum);
            }
        }
        return curList;
    }
}
