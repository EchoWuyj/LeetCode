package alg_02_train_dm._03_day_二维数组_二刷;

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

        示例 1:
        输入: numRows = 5
        输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

        元素          对应行数
         1             1 行
         1 1           2 行
         1 2 1         3 行
         1 3 3 1       4 行
         1 4 6 4 1     5 行

        示例 2:
        输入: numRows = 1
        输出: [[1]]

        提示:
        1 <= numRows <= 30

        KeyPoint 分析规律：
        每行的元素个数等于当前行索引+1
        每行的第一个元素和最后一个元素值都等于1
     */

    // KeyPoint 很好理解的方法 => 推荐
    public List<List<Integer>> generate(int numRows) {
        // 结果集
        List<List<Integer>> res = new ArrayList<>();
        // row 表示每行
        for (int row = 0; row < numRows; row++) {
            List<Integer> curList = new ArrayList<>();
            // row 和 col 对应关系 => row 为第 2 行，则该行有 2 个元素
            for (int col = 0; col <= row; col++) {
                // KeyPoint 补充说明：若 row = 0
                // row = 0，执行 if 之后，内层 for 循环条件 col <= row 满足
                // for 循环就已经结束了，不会执行该 else 分支

                // 首尾位置，添加元素 1
                if (col == 0 || col == row) {
                    curList.add(1);
                } else {
                    // 除了首尾位置，剩下的就是中间位置 => 添加元素为 [col-1] + [col]
                    // 获取上一层 list
                    List<Integer> preRowList = res.get(row - 1);
                    // 通过 preRowList 获取值
                    int curNum = preRowList.get(col - 1) + preRowList.get(col);
                    curList.add(curNum);
                }
            }
            res.add(curList);
        }
        return res;
    }
}
