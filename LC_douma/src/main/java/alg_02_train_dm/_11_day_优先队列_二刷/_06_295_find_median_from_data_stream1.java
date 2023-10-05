package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 20:21
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream1 {
     /*
        295：数据流的中位数
        中位数是 有序列表 中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

        例如
        [2,3,4] 的中位数是 3
        [2,3] 的中位数是 (2 + 3) / 2 = 2.5

        设计一个支持以下两种操作的数据结构：
        void addNum(int num) - 从数据流中添加一个整数到数据结构中。
        double findMedian() - 返回目前所有元素的中位数。

        提示:
        -10^5 <= num <= 10^5
        在调用 findMedian 之前，数据结构中至少有一个元素
        最多 5*10^4 次调用 addNum 和 findMedian

        进阶：
         1.如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
         2.如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

     */

    // KeyPoint 方法一 普通排序 => 超时
    class MedianFinder {
        List<Integer> list;

        // 初始化数据结构
        public MedianFinder() {
            list = new ArrayList<>();
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：O(1)
        public void addNum(int num) {
            list.add(num);
        }

        // 返回目前所有元素的中位数
        // 时间复杂度：O(nlogn)
        // KeyPoint 适用场景 => 适用于 addNum 次数非常多，但是 findMedian 次数很少
        public double findMedian() {

            // 1.若 n 奇数
            // n = 5
            // nums
            // index 0 1 2 3 4
            // value 1 2 3 4 5
            //           ↑
            //    索引：5/2=2
            //         中位数

            // 2.若 n 偶数
            // n = 6
            // nums
            // index 0 1 2 3 4 5
            // value 1 2 3 4 5 6
            //           ↑ ↑
            //  索引：(n-1)/2 与 (n/2)
            //          中位数

            Collections.sort(list);
            int n = list.size();
            // 奇数
            if (n % 2 == 1) {
                return list.get(n / 2);
            } else {
                // 偶数
                return (list.get((n - 1) / 2) + list.get(n / 2)) * 0.5;
            }
        }
    }
}
