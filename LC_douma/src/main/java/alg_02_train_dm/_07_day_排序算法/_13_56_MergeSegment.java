package alg_02_train_dm._07_day_排序算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:17
 * @Version 1.0
 */
public class _13_56_MergeSegment {
      /*
        56. 合并区间
        以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
        。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

        示例 1：
        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

        提示：
        1 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^4
     */

    // 字节面试官: 先出道基础的考考你
    public int[][] merge(int[][] intervals) {

        // 1. 按照区间左边的值进行升序排列 => 数据预处理
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 左边的值，升序排列
                return o1[0] - o2[0];
            }
        });

        // 2. 初始化 res, 用于存储合并之后区间结果的动态数组
        ArrayList<int[]> res = new ArrayList<>();

        // 3. 遍历处理每一个区间
        for (int i = 0; i < intervals.length; i++) {
            int[] currInterval = intervals[i];
            // res 为空，直接加入第一个区间
            if (res.isEmpty()) {
                res.add(currInterval);
            } else {
                // 判断是否有重叠，有的话则合并
                // 处理一个区间时和 res 中最后一个区间进行比较
                int[] lastInterval = res.get(res.size() - 1);
                int lastRight = lastInterval[1];
                int curLeft = currInterval[0];
                // 没有重叠，直接将 currInterval 加入 res
                if (lastRight < curLeft) {
                    res.add(currInterval);
                } else {
                    // 重叠，合并
                    int currRight = currInterval[1];
                    // 将最大值赋值给 lastRight，从而实现区间合并
                    lastInterval[1] = Math.max(lastRight, currRight);
                }
            }
        }
        // 将集合转数组
        return res.toArray(new int[res.size()][]);
    }
}
