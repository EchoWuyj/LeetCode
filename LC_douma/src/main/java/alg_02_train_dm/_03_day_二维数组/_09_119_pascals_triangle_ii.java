package alg_02_train_dm._03_day_二维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 19:58
 * @Version 1.0
 */
public class _09_119_pascals_triangle_ii {

    /*
        119. 杨辉三角 II
        给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
        在「杨辉三角」中，每个数是它左上方和右上方的数的和。
        进阶：你可以优化算法 O(k) 空间复杂度
     */

    // KeyPoint 方法一 直接模拟
    // 杨辉三角使用空间大小，二维数组的下半角，空间复杂度 O([k^2]/2) => O(k^2)
    // 空间复杂度 O(k^2)
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
        // 返回指定 rowIndex 行对应的集合
        return res.get(rowIndex);
    }

    // KeyPoint 方法二 优化 => 压缩存储空间
    // 计算当前行，只是依赖前面一行，故只需要记录前面一行即可，不用记录 res，只要 preRow 即可
    // 空间复杂度：O(k^2)
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> preRow = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            // 每次申请数组(最长为 k)，且申请 k 次，空间复杂度 O(k^2)
            List<Integer> curRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curRow.add(1);
                } else {
                    curRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            // 将当前行，赋值给前一行
            preRow = curRow;
        }
        return preRow;
    }

    // KeyPoint 方法三：优化成只用一个数组 O(k)
    // 1 3 3 1 1
    //         ↑ 从后往前遍历累和，从而求解下一行
    // 1 4 6 4 1
    public List<Integer> getRow(int rowIndex) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            // 一开始 ArrayList 容量可能不够，需要不断的 add，才会不断地扩容
            curRow.add(0);
            // 从后往前遍历，累加 [col-1] + [col]
            for (int col = row; col > 0; col--) {
                curRow.set(col, curRow.get(col - 1) + curRow.get(col));
            }
        }
        return curRow;
    }
}
