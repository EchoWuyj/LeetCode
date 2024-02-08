package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 20:40
 * @Version 1.0
 */
public class _34_56_merge {

    // 合并区间
    // 排序 + 直接模拟
    public int[][] merge(int[][] intervals) {

        // 最开始先使用集合装数组，因为不知道数组个数，最后返回时将集合转二维数组
        ArrayList<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            // 集合转二维数组，同时定义大小
            // 在二维数组中，第一个位置 [] 限制数组大小
            return res.toArray(new int[res.size()][]);
        }

        // 按照数组中第一元素升序排列
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        // 遍历 intervals 区间每个元素
        for (int i = 0; i < intervals.length; i++) {
            // 获取当前区间数组
            int[] cur = intervals[i];
            if (res.isEmpty()) {
                res.add(cur);
            } else {
                // 获取结果集中最后一个区间数组
                int[] last = res.get(res.size() - 1);
                // "最后的右" 和 "当前的左" 进行比较
                int lastRight = last[1];
                int curLeft = cur[0];
                // | lastLeft ... lastRight | < | curLeft ... curRight |
                // 若 lastRight < curLeft，则说明 cur 和 last 严格没有交集，直接添加 cur
                if (lastRight < curLeft) {
                    res.add(cur);
                } else {
                    // | lastLeft ... lastRight |
                    //           | curLeft ... curRight |
                    // => 比较 left 和 right 的 right 大，将其赋值给 last[1]
                    int curRight = cur[1];
                    // 赋值给 last 的 right
                    last[1] = Math.max(curRight, lastRight);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
