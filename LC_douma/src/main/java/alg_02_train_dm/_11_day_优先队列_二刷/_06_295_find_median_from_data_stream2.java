package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:38
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream2 {

    // KeyPoint 方法二 插入排序 => 超时
    class MedianFinder {
        List<Integer> list;

        public MedianFinder() {
            list = new ArrayList<>();
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：O(n)
        public void addNum(int num) {
            if (list.isEmpty()) {
                list.add(num);
            } else {
                // 插入排序
                // add 过程就将数据顺序维护好，后面 findMedian 操作很快
                int n = list.size();
                list.add(Integer.MIN_VALUE);
                int j = n;
                for (; j > 0; j--) {
                    if (num < list.get(j - 1)) {
                        list.set(j, list.get(j - 1));
                    } else {
                        break;
                    }
                }
                list.set(j, num);
            }
        }

        // 返回目前所有元素的中位数
        // 时间复杂度：O(1)
        // KeyPoint 适用场景 => 适用于 findMedian 次数非常多，但是 addNum 次数很少
        public double findMedian() {
            int n = list.size();
            if (n % 2 == 1) {
                return list.get(n / 2);
            } else {
                return (list.get((n - 1) / 2) + list.get(n / 2)) * 0.5;
            }
        }
    }
}
