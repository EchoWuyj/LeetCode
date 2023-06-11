package alg_03_leetcode_top_wyj.class_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 19:50
 * @Version 1.0
 */
public class Problem_0056_MergeIntervals {
    public static class Range {
        int start;
        int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        Range[] arr = new Range[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            arr[i] = new Range(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(arr, new Comparator<Range>() {
            @Override
            public int compare(Range r1, Range r2) {
                return r1.start - r2.start;
            }
        });

        int s = arr[0].start;
        int e = arr[0].end;
        ArrayList<Range> res = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start > e) {
                res.add(new Range(s, e));
                s = arr[i].start;
                e = arr[i].end;
            } else {
                e = Math.max(e, arr[i].end);
            }
        }
        res.add(new Range(s, e));
        return generateMatrix(res);
    }

    public static int[][] generateMatrix(ArrayList<Range> list) {
        int[][] arr = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = new int[]{list.get(i).start, list.get(i).end};
        }
        return arr;
    }
}
