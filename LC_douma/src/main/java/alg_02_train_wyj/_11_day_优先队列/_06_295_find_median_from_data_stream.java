package alg_02_train_wyj._11_day_优先队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 20:28
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream {
}

class MedianFinder1 {
    private List<Integer> data;

    public MedianFinder1() {
        data = new ArrayList<>();
    }

    public void addNum(int num) {
        data.add(num);
    }

    public double findMedian() {
        int n = data.size();
        Collections.sort(data);
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

class MedianFinder2 {
    private List<Integer> data;

    public MedianFinder2() {
        data = new ArrayList<>();
    }

    public void addNum(int num) {
        if (data.isEmpty()) {
            data.add(num);
        } else {
            int n = data.size();
            data.add(Integer.MIN_VALUE);
            int j = n;
            for (; j > 0; j--) {
                if (num < data.get(j - 1)) {
                    data.set(j, data.get(j - 1));
                } else {
                    break;
                }
            }
            data.set(j, num);
        }
    }

    public double findMedian() {
        int n = data.size();
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

class MedianFinder3 {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder3() {
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }
}

class MedianFinder4 {
    private TreeSet<int[]> data;
    private Comparator<int[]> customComparator;
    int index;
    private int[] lower, upper;

    public MedianFinder4() {
        customComparator = (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        };
        data = new TreeSet<>(customComparator);
        lower = null;
        upper = null;
        index = 0;
    }

    public void addNum(int num) {
        int[] cur = new int[]{num, index++};
        data.add(cur);

        if (data.size() == 1) {
            lower = data.first();
            upper = data.first();
        } else if (customComparator.compare(lower, upper) == 0) {
            if (customComparator.compare(cur, lower) < 0) {
                lower = data.lower(lower);
            } else {
                upper = data.higher(upper);
            }
        } else {
            if (customComparator.compare(cur, lower) < 0) {
                upper = lower;
            } else if (customComparator.compare(cur, upper) > 0) {
                lower = upper;
            } else {
                upper = cur;
                lower = cur;
            }
        }
    }

    public double findMedian() {
        if (lower == null) {
            return 0.0;
        }
        return (lower[0] + upper[0]) * 0.5;
    }
}

class MedianFinder5 {
    private int[] count;
    private int size;

    public MedianFinder5() {
        this.count = new int[101];
        this.size = 0;
    }

    public void addNum(int num) {
        count[num]++;
        size++;
    }

    public double findMedian() {
        int cnt = 0;
        int preCnt = 0;
        boolean isEven = size % 2 == 0;
        int lastNoneZero = 0;
        int lower = 0;
        int upper = 0;
        for (int num = 0; num < 101; num++) {
            cnt += count[num];
            if (cnt >= size / 2 + 1) {
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

    public static void main(String[] args) {
        MedianFinder5 medianFinder5 = new MedianFinder5();
        medianFinder5.addNum(1);
        System.out.println(medianFinder5.findMedian()); // 1.0
        medianFinder5.addNum(2);
        System.out.println(medianFinder5.findMedian()); // 1.5
        medianFinder5.addNum(3);
        System.out.println(medianFinder5.findMedian()); // 2.0
    }
}






