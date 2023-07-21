package alg_02_train_dm._03_day_二维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 16:33
 * @Version 1.0
 */
public class _09_119_pascals_triangle_ii3 {

    // KeyPoint 方法三：优化 => 只用一个数组 O(k)
    public List<Integer> getRow(int rowIndex) {

        // 将前面一行数据存储在 curList 中
        // 计算当前行数据时，从后往前遍历，计算当前行的值

        // 前一行数据：1 3 3 1 1
        //                    ↑ 从后往前遍历累和，从而求解下一行
        // 当前行数据：1 4 6 4 1

        // 1
        // 1 1
        // 1 2 1
        // 1 3 3 1
        // 1 4 6 4 1

        List<Integer> curList = new ArrayList<>();
        // 杨辉三角，最开始第 1 行的 1
        curList.add(1);
        for (int row = 1; row <= rowIndex; row++) {
            // 累加 [col-1]+[col] 之前，先添加 0，保证 curList.get(col) 索引不越界
            // 否则，curList 中元素只有一个，curList.get(1)，必然越界
            curList.add(0);
            // 从后往前遍历，累加 [col-1]+[col]
            // 注意：col > 0，不用对 col = 0 赋值，curList[0] 始终为 1
            for (int col = row; col > 0; col--) {
                int curNum = curList.get(col - 1) + curList.get(col);
                // 赋值给 col
                curList.set(col, curNum);
            }
        }
        return curList;
    }
}
