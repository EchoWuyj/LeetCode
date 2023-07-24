package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:38
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream3 {

    // KeyPoint 方法三 大顶堆 + 小顶堆 => 最优解
    // 注意：解决中位数比较通用的做法，遇到中位数题目联想这种解法，面试过程需要掌握的
    class MedianFinder {

        // 1.n 为偶数
        // nums：3 9 4 5 8 2 => 排序：2 3 4 5 8 9
        // 关键看：排序后中间两个元素

        // 2 3 4  |  5 8 9
        //     ↑     ↑
        //  较小值  较大值
        //  最大值  最小值
        //     ↓     ↓
        //  大顶堆  小顶堆

        // 2.n 为偶数
        // nums：6 3 9 4 5 8 2 => 排序：2 3 4 5 6 8 9
        // 关键看：排序后中间元素

        // 将中间元素放到大顶堆中

        // 2 3 4 5 | 6 8 9
        //       ↑
        //     较小值
        //     最大值
        //       ↓
        //     大顶堆

        // KeyPoint 总结
        // 1.若元素的个数是偶数，则大顶堆和小顶堆中元素各存一半
        // 2.若元素的个数是奇数，大顶堆中的元素个数比小顶堆中元素个数多 1

        // KeyPoint 类中一般只是定义变量，初始化在构造方法中执行
        // 大顶堆 => 存储较小的一半元素
        private PriorityQueue<Integer> maxHeap;
        // 小顶堆 => 存储较大的一半元素
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：log(n)
        public void addNum(int num) {
            // KeyPoint 注意
            // 将第一个元素存在'大顶堆'中，因为奇数个元素，大顶堆比小顶堆要多一个元素的
            if (maxHeap.isEmpty()) {
                // 第一元素，加入 maxHeap 后，直接 return，结束 addNum 方法
                // 不需要执行：大顶堆和小顶堆之间元素调整
                maxHeap.add(num);
                return;
            }

            // 不是第一个元素，通过num 和 maxHeap 堆顶比较
            // 从而决定去往 maxHeap 还是 minHeap

            // 1.较小元素 => 存入大顶堆
            // 较小值存大顶堆 => 获取较小值中叫较大值
            if (num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                // 2.较大元素 => 存入小顶堆
                // 较大值存小顶堆 => 获取较大值中叫较小值
                minHeap.add(num);
            }

            // 大顶堆和小顶堆之间元素调整
            // => 大顶堆中元素个数比小顶堆中元素个数，只能多 1 个，否则就要调整

            // KeyPoint 特别注意
            // 两个 if 判断是相互独立的，都是需要顺序执行的，不存在 if else 判断逻辑

            // 1.大顶堆元素多了，往小顶堆中调整
            // maxHeap 最好，个数 大于 minHeap 只能是 1 个
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }

            // 2.小顶堆元素多了，往大顶堆中调整
            // maxHeap 最差，个数和 minHeap 相等
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.remove());
            }
        }

        // 返回目前所有元素的中位数
        // 时间复杂度：O(1)
        public double findMedian() {
            // 奇数
            if (maxHeap.size() > minHeap.size()) {
                // 大顶堆堆顶
                return maxHeap.peek();
                // 偶数
            } else {
                // 小顶堆堆顶 + 大顶堆堆顶，整体在除以一半
                return (maxHeap.peek() + minHeap.peek()) * 0.5;
            }
        }
    }
}
