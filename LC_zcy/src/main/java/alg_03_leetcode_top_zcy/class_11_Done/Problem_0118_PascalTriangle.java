package alg_03_leetcode_top_zcy.class_11_Done;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 16:04
 * @Version 1.0
 */

// 杨辉三角
public class Problem_0118_PascalTriangle {
    
    /*
        给定一个非负整数numRows,生成「杨辉三角」的前numRows行
        在「杨辉三角」中,每个数是它左上方和右上方的数的和。

        输入:numRows=5
        输出:[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

                [1]
               [1,1]
              [1,2,1]   每行开头和结尾都是1,单独使用for循环添加1
             [1,3,3,1]
            [1,4,6,4,1]
     */

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            // 第一列设置1
            ans.get(i).add(1);
        }

        // 从第2行开始遍历,但真正计算是从第3行第2个元素开始
        // 第2行第2个元素属于两侧的1
        for (int i = 1; i < numRows; i++) {
            // 从该行第2个元素开始计算
            for (int j = 1; j < i; j++) {
                // i行j列值 = (i-1)行(j-1)列 + (i-1)行j列
                ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            // 最后一列设置1
            ans.get(i).add(1);
        }
        return ans;
    }
}
