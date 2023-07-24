package alg_02_train_wyj._11_day_优先队列;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-07-23 14:37
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream4 {
    class MedianFinder {
        TreeSet<int[]> tree;
        Comparator<int[]> comparator;
        int[] upper;
        int[] lower;
        int index;

        public MedianFinder() {
            comparator = ((a, b) ->
                    a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

            tree = new TreeSet<>(comparator);
            lower = null;
            upper = null;
            index = 0;
        }

        public void addNum(int num) {
            int[] cur = new int[]{num, index++};
            tree.add(cur);

            if (tree.size() == 1) {
                upper = cur;
                lower = cur;
            } else if (comparator.compare(upper, lower) == 0) {
                if (comparator.compare(cur, lower) < 0) {
                    lower = tree.lower(lower);
                } else {
                    upper = tree.higher(upper);
                }
            } else {
                if (comparator.compare(cur, lower) < 0) {
                    upper = lower;
                } else if (comparator.compare(cur, upper) > 0) {
                    lower = upper;
                } else {
                    lower = cur;
                    upper = cur;
                }
            } // 偶数并且中间两个元素不是同一个元素
        }

        public double findMedian() {
            if (lower == null) {
                return 0.0;
            } else {
                return (lower[0] + upper[0]) * 0.5;
            }
        }
    }

    public static void main(String[] args) {

    }
}
