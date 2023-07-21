package alg_02_train_dm._03_day_二维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 16:33
 * @Version 1.0
 */
public class _09_119_pascals_triangle_ii2 {

    // KeyPoint 方法二 优化 => 压缩存储空间
    // 计算当前行，只是依赖前面一行，故只需通过 preList 记录前面一行即可，即不用 res 来记录每一行
    // 空间复杂度：O(k^2)
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> preList = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            // 每次申请数组(最长为 k)，且申请 k 次，空间复杂度 O(k^2)
            List<Integer> curList = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    curList.add(1);
                } else {
                    curList.add(preList.get(col - 1) + preList.get(col));
                }
            }
            // 将当前行，赋值给前一行
            preList = curList;
        }
        // 最后返回 preList，因为 curList 已经赋值给 preList 了
        return preList;
    }
}
