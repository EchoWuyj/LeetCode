package alg_03_leetcode_top_zcy.class_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 12:40
 * @Version 1.0
 */

// 合并区间
public class Problem_0056_MergeIntervals {

    /*
       思路
       1)多个区间[],[],[]...先根据区间的开始位置进行排序,从小到大,通过比较器实现
       2)[1,5],[1,4],[9,12]
       3)定义range类,start表开始位置,end表示结束位置
       4)[1,5]和[1,4]比较,1是否比5大,若没有则原来[1,5]可以包含[1,4]的start
         同时[1,4]的新end能否推高旧end,若没有推高,则什么也不管
         继续比较[9,12],新start>旧end,将[1,5]生成.新start=9,新end=12
     */

    public static class Range {
        public int start;
        public int end;

        public Range(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class RangeComparator implements Comparator<Range> {

        @Override
        public int compare(Range o1, Range o2) {
            return o1.start - o2.start;
        }
    }

    // intervals  N * 2
    // 二维数组的行:元素个数
    //          列:元素是多大数组
    // N表示N个[]
    // 2表示[]有两个元素
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        // 定义range数组
        Range[] arr = new Range[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            // 第一个[]转range,并存在arr中
            arr[i] = new Range(intervals[i][0], intervals[i][1]);
        }
        // 指定比较器(start从小到大)
        Arrays.sort(arr, new RangeComparator());
        // 遍历过程生成的range是不固定的
        ArrayList<Range> ans = new ArrayList<>();
        int s = arr[0].start;
        int e = arr[0].end;
        // 遍历range数组,从索引1开始
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start > e) {
                // 旧range存到ans中
                ans.add(new Range(s, e));
                // 新建一个range
                s = arr[i].start;
                e = arr[i].end;
            } else {
                // 是否能推高end
                e = Math.max(e, arr[i].end);
            }
        }
        // 新start>旧end,将之前range加入ans
        // 最终没有更新(比较)range是处于没有添加的状态
        ans.add(new Range(s, e));
        // 转二维数组
        return generateMatrix(ans);
    }

    // 集合转二维数组
    public static int[][] generateMatrix(ArrayList<Range> list) {
        int[][] matrix = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            matrix[i] = new int[]{list.get(i).start, list.get(i).end};
        }
        return matrix;
    }
}
