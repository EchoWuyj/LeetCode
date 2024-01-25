package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 20:40
 * @Version 1.0
 */
public class _34_56_merge {
    public int[][] merge(int[][] intervals) {
        // 先使用集合装数组，最后返回时将集合转数组
        ArrayList<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            // 二维数组，同时定义大小
            return res.toArray(new int[res.size()][]);
        }

//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });

        // 简单写法 => 推荐使用
        // 按照数组中第一元素升序排列
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

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
                // lastRight < curLeft 说明 cur 和 last 严格没有交集，直接添加 cur
                if (lastRight < curLeft) {
                    res.add(cur);
                } else {
                    // 比较谁的 right 大
                    int curRight = cur[1];
                    // 赋值给 last 的 right
                    last[1] = Math.max(curRight, lastRight);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
