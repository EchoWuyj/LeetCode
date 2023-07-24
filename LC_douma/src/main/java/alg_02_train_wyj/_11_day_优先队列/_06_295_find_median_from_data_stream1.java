package alg_02_train_wyj._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:46
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream1 {
    class MedianFinder {
        List<Integer> list;

        public MedianFinder() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
        }

        public double findMedian() {
            Collections.sort(list);
            int n = list.size();
            if (n % 2 == 1) {
                return list.get(n / 2);
            } else {
                return (list.get(n / 2) + list.get((n - 1) / 2)) * 0.5;
            }
        }
    }
}
