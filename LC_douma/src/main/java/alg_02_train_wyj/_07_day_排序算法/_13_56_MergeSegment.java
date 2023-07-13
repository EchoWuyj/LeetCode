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
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (list.isEmpty()) {
                list.add(cur);
            } else {
                int[] last = list.get(list.size() - 1);
                int lastRight = last[1];
                int curLeft = cur[0];
                if (lastRight < curLeft) {
                    list.add(cur);
                } else {
                    int curRight = cur[1];
                    last[1] = Math.max(lastRight, curRight);
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
