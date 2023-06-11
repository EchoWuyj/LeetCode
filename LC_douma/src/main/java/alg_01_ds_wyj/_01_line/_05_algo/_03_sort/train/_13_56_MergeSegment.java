package alg_01_ds_wyj._01_line._05_algo._03_sort.train;

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

        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            if (res.isEmpty()) {
                res.add(curInterval);
            } else {
                int[] lastInterval = res.get(res.size() - 1);
                int lastRight = lastInterval[1];
                int curLeft = curInterval[0];

                if (lastRight < curLeft) {
                    res.add(curInterval);
                } else {
                    int curRight = curInterval[1];
                    lastInterval[1] = Math.max(lastRight, curRight);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
