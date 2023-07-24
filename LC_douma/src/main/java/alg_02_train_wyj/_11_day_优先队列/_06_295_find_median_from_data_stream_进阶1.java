package alg_02_train_wyj._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-23 16:19
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream_进阶1 {

    static class MedianFinder {
        int[] count;
        int size;

        public MedianFinder() {
            this.count = new int[101];
            this.size = 0;
        }

        public void addNum(int num) {
            count[num]++;
            size++;
        }

        public double findMedian() {
            int cnt = 0, preCnt = 0;
            boolean isEven = size % 2 == 0;
            int lastNoneZero = 0;
            int lower = 0, upper = 0;

            for (int num = 0; num < 101; num++) {
                cnt += count[num];
                if (cnt >= (size / 2) + 1) {
                    upper = num;
                    if (isEven && preCnt + 1 == cnt) {
                        lower = lastNoneZero;
                    } else {
                        lower = upper;
                    }
                    break;
                }
                if (count[num] > 0) lastNoneZero = num;
                preCnt = cnt;
            }
            return (lower + upper) * 0.5;
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
