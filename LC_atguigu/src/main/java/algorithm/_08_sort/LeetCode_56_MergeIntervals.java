package algorithm._08_sort;

import algorithm._08_sort.basic_sort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2022-03-24 17:02
 * @Version 1.0
 */
public class LeetCode_56_MergeIntervals {
    // 按区间左边界排序
    public int[][] merge(int[][] intervals) {
        // 定义一个结果数组
        ArrayList<int[]> result = new ArrayList<>();
        // 1.将所有区间按照左边界排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 正序排列
                return o1[0] - o2[0];
            }
        });

        // 2.遍历排序后的区间,逐个合并
        for (int[] interval : intervals) {
            // 记录当前的左右边界
            int left = interval[0], right = interval[1];

            // 获取结果数组长度
            int length = result.size();

            // 如果left比最后一个区间的右边界大,不能合并,直接添加到结果
            if (length == 0 || left > result.get(length - 1)[1]) {
                result.add(interval);
            } else {
                // 可以合并,则mergeLeft为上一个合并的区间的左边界
                int mergeLeft = result.get(length - 1)[0];
                //上一个右边界和当前的右边界中去最大值为现在的右边界
                int mergeRight = Math.max(result.get(length - 1)[1], right);
                //将合并后的区间赋值给length-1的位置
                result.set(length - 1, new int[]{mergeLeft, mergeRight});
            }
        }

        // 将集合转成数组
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        LeetCode_56_MergeIntervals mergeIntervals = new LeetCode_56_MergeIntervals();

        for (int[] interval : mergeIntervals.merge(intervals)) {
            QuickSort.printArray(interval);
        }
    }
}
