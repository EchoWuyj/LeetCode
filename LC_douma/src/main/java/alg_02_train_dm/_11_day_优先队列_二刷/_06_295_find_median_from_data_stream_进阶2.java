package alg_02_train_dm._11_day_优先队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:39
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream_进阶2 {

    // KeyPoint 进阶 2：
    // 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
    // => 99% 的整数都在 0 到 100 范围内，意味着中位数肯定是在 [0，100] 内
    //    需要做的就是统计好有多少个小于 0 的元素，有多少个大于 100 的元素
    // => 视频讲的不是很清楚，了解即可，思路基本和之前一样

    // 计数排序
    class MedianFinder6 {
        private int[] count;
        private int size;

        // 记录小于 0 的个数
        private int countLessZero;

        public MedianFinder6() {
            this.count = new int[101];
            this.size = 0;
            this.countLessZero = 0;
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：O(1)
        public void addNum(int num) {
            if (num < 0) countLessZero++;
            else if (num >= 0 && num <= 100) count[num]++;
            size++;
        }

        // 返回目前所有元素的中位数
        // 时间复杂度：O(101) -> O(1)
        public double findMedian() {
            int cnt = countLessZero;
            boolean isEven = size % 2 == 0;
            int lastNonZero = 0;
            int lower = 0, upper = 0;
            for (int num = 0; num < 101; num++) {
                if (count[num] > 0) lastNonZero = num;
                cnt += count[num];
                if (cnt >= size / 2 + 1) {
                    upper = num;
                    if (isEven) {
                        lower = lastNonZero;
                    } else {
                        lower = upper;
                    }
                    break;
                }
            }
            return (lower + upper) * 0.5;
        }
    }
}
