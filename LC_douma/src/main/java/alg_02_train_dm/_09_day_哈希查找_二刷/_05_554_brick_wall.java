package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 16:40
 * @Version 1.0
 */
public class _05_554_brick_wall {
    /*
        554 号算法题：砖墙
        你的面前有一堵矩形的、由 n 行 砖块 组成的砖墙。
        这些砖块高度相同 但是宽度不同。你现在要画一条 自顶向下 的、穿过最少砖块 的垂线。

        砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。
        如果你画的线 只是从砖块的边缘经过，就不算穿过这块砖。

        你需要找出怎样画 才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
        你不能沿着 墙的两个垂直边缘之一 画线，这样显然是没有穿过一块砖的。

        注意
        1.每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
        2.每一行砖块的数量在[1，10，000]范围内，墙的高度在[1，10，000]范围内，总的砖块数量不超过20，000。

        给你一个二维数组 wall，该数组包含这堵墙的相关信息。
        其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。

        示例 1：
        输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        输出：2

        示例 2：
        输入：wall = [[1],[1],[1]]
        输出：3

        提示：
        n == wall.length
        1 <= n <= 104
        1 <= wall[i].length <= 104
        1 <= sum(wall[i].length) <= 2 * 104
        对于每一行 i ，sum(wall[i]) 是相同的
        1 <= wall[i][j] <= 231 - 1

        KeyPoint 分析
        思路转换 => 为了尽可能少的穿过砖，垂直线穿过的边缘应该尽量的多
        关键 2 点
        1.边缘如何表达 => 砖块边缘距离 y 轴距离
        2.边缘如何计算 => 统计相同边缘出现次数，映射成 map
                          key：相同边缘
                          value：个数
                      => 找到相同边缘个数最多 => 画线的位置
     */

    public int leastBricks(List<List<Integer>> wall) {
        // 边缘频率 => 砖结尾位置出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0;
        int size = wall.size();
        // 遍历砖墙的每行
        for (int i = 0; i < size; i++) {
            // 左侧靠墙的位置
            int edgePos = 0;
            // 遍历当前行砖墙的每一列
            // 注意：按照题目要求，最后一块砖不算，故需要 wall.get(i).size() - 1
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                int curBrickLen = wall.get(i).get(j);
                // 每块砖结尾位置
                // 一轮 for 之后，前一块砖的结尾位置，即后一块砖的开始位置 => 累加的关系
                edgePos += curBrickLen;
                map.put(edgePos, map.getOrDefault(edgePos, 0) + 1);
                // 一边计算，一边比较最大值，获取出现最高频率边缘
                maxCnt = Math.max(maxCnt, map.get(edgePos));
            }
        }
        // 行数 - 最高频率边缘 = 穿过砖次数
        return wall.size() - maxCnt;
    }
}
