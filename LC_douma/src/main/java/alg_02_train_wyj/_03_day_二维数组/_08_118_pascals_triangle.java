package alg_02_train_wyj._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 20:10
 * @Version 1.0
 */
public class _08_118_pascals_triangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
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
        return res;
    }
}
