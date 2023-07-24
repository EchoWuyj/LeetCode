package alg_02_train_wyj._11_day_优先队列;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 20:09
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream2 {

    class MedianFinder {
        List<Integer> list;

        public MedianFinder() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            if (list.isEmpty()) {
                list.add(num);
            } else {
                int size = list.size();
                list.add(Integer.MIN_VALUE);
                int j = size;
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

        public double findMedian() {
            int n = list.size();
            if (n % 2 == 1) {
                return list.get(n / 2);
            } else {
                return (list.get(n / 2) + list.get((n - 1) / 2)) * 0.5;
            }
        }
    }
}
