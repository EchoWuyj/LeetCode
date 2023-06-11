package alg_02_train_dm._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 19:57
 * @Version 1.0
 */
public class _08_118_pascals_triangle {

    /*
        118. 杨辉三角
        给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行
        在「杨辉三角」中，每个数是它左上方和右上方的数的和。

        分析
        每行的元素个数等于当前行索引+1
        每行的第一个元素和最后一个元素值等于1
     */

    // 很好理解的方法 => 推荐
    public List<List<Integer>> generate(int numRows) {
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> curRow = new ArrayList<>();
            // row 和 col 对应关系
            for (int col = 0; col <= row; col++) {
                // 首尾位置，添加元素 1
                if (col == 0 || col == row) {
                    curRow.add(1);
                } else {
                    // row = 0，不会执行该 else 分支
                    // 中间位置，添加元素为 [col-1] + [col]
                    List<Integer> preRow = res.get(row - 1);
                    curRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            res.add(curRow);
        }
        return res;
    }
}
