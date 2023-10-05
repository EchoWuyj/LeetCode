package alg_02_train_wyj._07_day_排序算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:33
 * @Version 1.0
 */
public class _13_56_MergeSegment {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return res.toArray(new int[res.size()][]);

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (res.isEmpty()) {
                res.add(cur);
            } else {
                int[] last = res.get(res.size() - 1);
                int lastRight = last[1];
                int curLeft = cur[0];
                if (lastRight < curLeft) {
                    res.add(cur);
                } else {
                    int curRight = cur[1];
                    last[1] = Math.max(curRight, lastRight);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
