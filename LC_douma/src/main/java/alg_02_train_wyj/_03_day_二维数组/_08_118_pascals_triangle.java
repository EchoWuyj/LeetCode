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
        for (int i = 0; i < numRows; i++) {
            List<Integer> curList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    curList.add(1);
                } else {
                    List<Integer> preRowList = res.get(i - 1);
                    int curNum = preRowList.get(j) + preRowList.get(j - 1);
                    curList.add(curNum);
                }
            }
            res.add(curList);
        }
        return res;
    }
}
