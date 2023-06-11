package alg_02_train_dm._07_day_排序算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:18
 * @Version 1.0
 */
public class _14_57_insert_interval {

    /*
        57. 插入区间
        给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
        在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠
        （如果有必要的话，可以合并区间）。

        示例 1：
        输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
        输出：[[1,5],[6,9]]

        示例 2：
        输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        输出：[[1,2],[3,10],[12,16]]
        解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

        提示：
        0 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= intervals[i][0] <= intervals[i][1] <= 10^5
        intervals 根据 intervals[i][0] 按 升序 排列
        newInterval.length == 2
     */

    // 直接模拟
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 集合本身相当于一维数组，里面元素又是一维数组，整体就是二维数组
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;

        // 用于遍历所有的区间
        int i = 0;

        // 1. 将区间结束小于新区间开始的区间放入到结果集
        //     => 新插入的区间和原始区间，两个区间没有交集，即没有重叠
        while (i < n && intervals[i][1] < newInterval[0]) { // 可能有多个
            res.add(intervals[i]);
            i++;
        }

        // 2. 将区间开始小于等于新区间结束的区间和新区间合并
        //    => 退出上面 while 循环，intervals[i][1] >= newInterval[0]
        //    => 通过 intervals[i][0] > newInterval[1]，逆推得到 intervals[i][0] <= newInterval[1]
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // 确定 newInterval 的 [0] 和 [1]，分别取 min 和 max
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            // KeyPoint 注意 i 和 1 的区分，数字都在键盘的最上面一行
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        // 3. 将合并后的区间加入道结果集
        res.add(newInterval);

        // 4. 将剩余的区间放入到结果集
        // intervals[i][0] > newInterval[1] 严格大于，两个区间没有交集，即没有重叠
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }
        // 返回二维数组，集合转数组
        return res.toArray(new int[res.size()][]);
    }
}
