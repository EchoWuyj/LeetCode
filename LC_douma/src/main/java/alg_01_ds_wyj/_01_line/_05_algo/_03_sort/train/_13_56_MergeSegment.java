package alg_01_ds_wyj._01_line._05_algo._03_sort.train;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 13:33
 * @Version 1.0
 */
public class _13_56_MergeSegment {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals
                , (a, b) -> a[0] - b[0]);
        ArrayList<int[]> res = new ArrayList<>();
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] last = intervals[i];
            if (res.isEmpty()) {
                res.add(last);
            }else {
                int[] pre = res.get(res.size() - 1);
                int preRight = pre[1];
                int lastLeft = last[0];
                if (preRight < lastLeft) {
                    res.add(last);
                } else {
                    int lastRight = last[1];
                    pre[1] = Math.max(preRight, lastRight);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
