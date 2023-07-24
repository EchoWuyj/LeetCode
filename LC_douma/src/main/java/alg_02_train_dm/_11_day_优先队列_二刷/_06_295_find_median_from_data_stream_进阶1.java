package alg_02_train_dm._11_day_优先队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:39
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream_进阶1 {

    // KeyPoint 进阶 1
    // 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
    // => 数据范围小，且涉及排序 => 计数排序

    // KeyPoint 前置结论
    // 数组无论奇偶，排完序后 upper 都是在 (size/2+1) 个元素位置
    // 注意：这里表示第几个元素，不是索引位置

    // size 为奇数，size = 5
    //          upper
    //          lower
    //           ↓
    // index 1 2 3 4 5

    // size 为偶数，size = 6
    //       lower upper
    //           ↓ ↓
    // index 1 2 3 4 5 6，size = 6

    static class MedianFinder {
        // 计数排序数组
        int[] count;
        // 记录当前添加元素个数
        int size;

        public MedianFinder() {
            this.count = new int[101];
            this.size = 0;
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：O(1)
        public void addNum(int num) {
            count[num]++;
            size++;
        }

        // 返回目前所有元素的中位数
        // 时间复杂度：O(101) -> O(1)
        public double findMedian() {
            // 遍历 count 数组过程中，cnt 记录到当前数字为止，一共出现的数字的个数
            int cnt = 0;
            // preCnt 表示到前一个数字为止，一共出现的数字的个数
            int preCnt = 0;
            // 偶数 => 每次 addNum 操作之后，size 发生变化，奇偶性也发生变化
            boolean isEven = size % 2 == 0;

            // 记录上一个非零元素
            int lastNoneZero = 0;
            int lower = 0, upper = 0;

            // 遍历 count 中每个位置
            for (int num = 0; num < 101; num++) {
                // 累加每个元素出现次数
                cnt += count[num];

                // 即使 cnt 没有刚好等于 size/2+1，而是 cnt > size/2+1
                // 但是 upper 指向的 num 却是相同，故也是不影响的
                if (cnt >= size / 2 + 1) {
                    // 确定 upper
                    upper = num;

                    // 判断奇偶，确定 lower 位置
                    // 数组长度是偶数，上一轮 preCnt 和 本轮 cnt 相差 1
                    if (isEven && preCnt + 1 == cnt) {
                        lower = lastNoneZero;
                    } else {
                        // 注意：这两种情况，lower 和 upper 指针都是指向同一个位置
                        // 1. isEven && preCnt + 1 != cnt，
                        // 2. !isEven => 数组长度是奇数，upper 和 lower 是同一个位置
                        lower = upper;
                    }
                    // 执行 if 判断之后，已经确定 lower 和 upper
                    // 故后续 for 循环不需要执行了，直接 break
                    break;
                }
                // 只要 count[num] 大于 0，则说明 num 出现
                // 更新 lastNoneZero，从而实现记录上一个非零元素
                if (count[num] > 0) lastNoneZero = num;
                // 赋值 preCnt，为下轮循环做准备
                preCnt = cnt;
            }
            return (lower + upper) * 0.5;

            // KeyPoint bug 解释：preCnt + 1 == cnt

            //  arr1
            //  index 0 1 2 3 4 5 6 7
            //  value 1 2 2 2 2 2 2 8
            //                ↑
            //              upper = num = 2

            // 若单是一个 isEven 条件，没有 preCnt + 1 == cnt 限制
            // 则 lower = lastNoneZero => lower = 1，但实际上 lower = 2
            // 故需要加上 preCnt + 1 == cnt，从而保证：中间两个元素一定不是相同的

            // cnt = 7 => 到当前数字 2 为止，一共出现了 7 个数字
            // preCnt = 1 => 到前一个数字 1 为止，一共出现了 1 个数字
            // => preCnt + 1 != cnt，表明中间的两个数字相同，则 lower 和 upper 相等

            //  arr2
            //  index 0 1 2 3 4 5
            //  value 1 1 2 3 4 5
            //              ↑
            //            upper = num = 3

            // cnt = 4 => 到当前数字 3 为止，一共出现了 4 个数字
            // preCnt = 3 => 到前一个数字 2 为止，一共出现了 3 个数字
            // => preCnt + 1 == cnt，表明中间的两个数字不相同，可以使用 low = lastNoneZero

        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian()); // 1.0
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // 2.0
    }
}
