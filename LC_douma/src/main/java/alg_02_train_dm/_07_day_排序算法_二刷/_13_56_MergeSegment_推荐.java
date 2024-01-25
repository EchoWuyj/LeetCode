package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:17
 * @Version 1.0
 */
public class _13_56_MergeSegment_推荐 {
      /*
        56. 合并区间
        以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
        。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

        示例 1：
        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
        输出：[[1,6],[8,10],[15,18]]
        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]

        提示：
        1 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^4
     */

    // 典中典：字节面试官: 先出道基础的考考你
    public int[][] merge(int[][] intervals) {

        // 若是直接判断区间是否重叠，则两个区间的两个端点都要比较判断，比较麻烦
        // 故先对区间进行预处理

        // 1. 按照区间左边的值进行升序排列 => 数据预处理
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                // 左边的值，升序排列
//                return o1[0] - o2[0];
//            }
//        });

        // 左边的值，升序排列
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        // 2. 初始化 res, 用于存储合并之后区间结果的动态数组
        ArrayList<int[]> res = new ArrayList<>();

        // 3. 遍历处理每一个区间
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            // res 为空，直接加入第一个区间
            // KeyPoint 判断 res 为空，不是 null，而是 isEmpty，特别注意
            if (res.isEmpty()) {
                res.add(cur);
            } else {
                // 判断是否有重叠，有的话则合并
                // 处理一个区间时和 res 中最后一个区间进行比较
                int[] last = res.get(res.size() - 1);
                // 最后区间右
                int lastRight = last[1];
                // 当前区间左
                int curLeft = cur[0];

                // 集合最后一个元素右端 和 新加入元素左端 没有重叠，直接将 cur 加入 res
                if (lastRight < curLeft) {
                    res.add(cur);
                } else {
                    // 重叠，合并
                    int curRight = cur[1];
                    // 更新 last 区间
                    // 1.last[0] 保持不变
                    // 2.更新 last[1]，将 curRight 和 lastRight 较大值赋值给 last[1]，从而实现区间合并
                    last[1] = Math.max(lastRight, curRight);
                }
            }
        }
        // 将集合转数组(二维数组)
        // res 泛型是引用数据类型 int[]，故可以直接转二维数组
        return res.toArray(new int[res.size()][]);

        // KeyPoint 区别：将集合转数组(一维)
        // 集合转一维数组，只是引用类型数组 Integer[]，不能是基本数据类型 int []
        // 因为 ArrayList<Integer> data 集合中是 Integer 类型，不是 int 类型
        // Integer[] dataArr = data.toArray(new Integer[data.size()]);

    }
}
