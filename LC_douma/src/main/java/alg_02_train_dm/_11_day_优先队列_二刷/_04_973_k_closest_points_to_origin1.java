package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 15:38
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin1 {
       /*
           973：最接近原点的 K 个点
           我们有一个由平面上的点组成的列表 points，需要从中找出 K 个距离原点 (0, 0) 最近的点
           （这里，平面上两点之间的距离是欧几里德距离）
           你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

           补充：欧几里德距离 d = sqrt((x2 - x1)^2 + (y2 - y1)^2)

           输入：points = [[3,3],[5,-1],[-2,4]], K = 2
                3*3 + 3*3 = 18 => 这里统一省略 sqrt
                5*5 + -1*(-1) = 26
               -2*(-2) + 4 * 4 = 20
           输出：[[-2,4], [3,3]]

           提示：
           1 <= K <= points.length <= 10000
           -10000 < points[i][0] < 10000
           -10000 < points[i][1] < 10000
     */

    // KeyPoint 方法一 数学
    // 按照欧几里得距离排序
    public int[][] kClosest1(int[][] points, int k) {
        if (points == null) return points;
        // 升序排列
        // KeyPoint 注意事项
        // 1.两部分相减，需要整体加上括号，否则就不是升序的逻辑了
        // 2.这里统一省略 sqrt
        Arrays.sort(points, (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));

        // 数组中前 k 个点
        // => 最接近原点的 k 个点 => 数组升序排列，且为前 k 个
        // from 位置 => 包括，to 位置 => 不包括
        // => 实际范围：[0,k-1] 表示 k 个元素
        return Arrays.copyOfRange(points, 0, k);
    }
}
