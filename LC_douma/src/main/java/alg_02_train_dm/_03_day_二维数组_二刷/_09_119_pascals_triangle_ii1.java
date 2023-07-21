package alg_02_train_dm._03_day_二维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 19:58
 * @Version 1.0
 */
public class _09_119_pascals_triangle_ii1 {

    /*
        119. 杨辉三角 II
        给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
        在「杨辉三角」中，每个数是它左上方和右上方的数的和。
        进阶：你可以优化算法 O(k) 空间复杂度

        示例 1:
        输入: rowIndex = 3
        输出: [1,3,3,1]

         1
         1 1
         1 2 1
         1 3 3 1
         1 4 6 4 1

        示例 3:
        输入: rowIndex = 1
        输出: [1,1]

        提示:
        0 <= rowIndex <= 33
     */

    // KeyPoint 方法一 直接模拟
    // 杨辉三角使用空间大小，二维数组的下半角，假设最后一行元素个数为 k
    // 空间复杂度 O([k^2]/2) => O(k^2)
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            List<Integer> curList = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curList.add(1);
                } else {
                    List<Integer> preList = res.get(row - 1);
                    int curNum = preList.get(col - 1) + preList.get(col);
                    curList.add(curNum);
                }
            }
            res.add(curList);
        }
        // 返回指定 rowIndex 行对应的集合
        return res.get(rowIndex);
    }
}
