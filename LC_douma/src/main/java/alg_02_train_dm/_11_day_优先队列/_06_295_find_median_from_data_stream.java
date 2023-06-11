package alg_02_train_dm._11_day_优先队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 20:21
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream {
     /*

        295 号算法题：数据流的中位数
        中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

        例如
        [2,3,4] 的中位数是 3
        [2,3] 的中位数是 (2 + 3) / 2 = 2.5

        设计一个支持以下两种操作的数据结构：
             void addNum(int num) - 从数据流中添加一个整数到数据结构中。
             double findMedian() - 返回目前所有元素的中位数。

        进阶：
         1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
         2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？


         提示:
         -10^5 <= num <= 10^5
         在调用 findMedian 之前，数据结构中至少有一个元素
         最多 5 * 10^4 次调用 addNum 和 findMedian
     */
}

// KeyPoint 方法一 普通排序
class MedianFinder1 {
    private List<Integer> data;

    // 初始化数据结构
    public MedianFinder1() {
        data = new ArrayList<>();
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：O(1)
    public void addNum(int num) {
        data.add(num);
    }

    // 返回目前所有元素的中位数
    // 时间复杂度：O(nlogn)
    public double findMedian() {
        Collections.sort(data);
        int n = data.size();
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

// KeyPoint 方法二 插入排序
class MedianFinder2 {
    private List<Integer> data;

    public MedianFinder2() {
        data = new ArrayList<>();
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：O(n)
    public void addNum(int num) {
        if (data.isEmpty()) {
            data.add(num);
        } else {
            // 插入排序 => add 过程就将数据顺序维护好，后面 findMedian 操作很快
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

    // 返回目前所有元素的中位数
    // 时间复杂度：O(1)
    public double findMedian() {
        int n = data.size();
        if (n % 2 == 1) {
            return data.get(n / 2);
        } else {
            return (data.get((n - 1) / 2) + data.get(n / 2)) * 0.5;
        }
    }
}

// KeyPoint 方法三 大顶堆 + 小顶堆 => 解决中位数比较通用的做法，遇到中位数题目联想这种解法
class MedianFinder3 {

    // 2 3 4  | 5 8 9
    // 大顶堆  | 小顶堆

    // 大顶堆 => 存储较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆 => 存储较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // 注意
    // 1.若元素的个数是偶数，则大顶堆和小顶堆中元素各存一半
    // 2.若元素的个数是奇数，大顶堆中的元素个数比小顶堆中元素个数多 1

    public MedianFinder3() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：log(n)
    public void addNum(int num) {
        // 第一个元素存在'大顶堆'中，maxHeap.add(num) 操作后，直接 return
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        // 小的元素 => 大顶堆
        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
            // 大的元素 => 小顶堆
        } else {
            minHeap.add(num);
        }

        // KeyPoint 大顶堆和小顶堆之间元素调整
        // 1.大顶堆元素多了，往小顶堆中调整
        // 注意：大顶堆中的元素个数比小顶堆中元素个数，只能多 1 个，否则就要调整
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }

        // 2.小顶堆元素多了，往大顶堆中调整
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // 返回目前所有元素的中位数
    // 时间复杂度：O(1)
    public double findMedian() {
        // 奇数
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
            // 偶数
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }
}

// KeyPoint 方法四 红黑树 => 开阔视野，实现比较复杂，不推荐
// 总结：面试过程，没有必要自己主动写出来，当面试官问到你，你再假装若有所思地写出来，演戏嘛
// 一般普通的二叉查找树是不能保证做任何操作时间复杂度都是 log 级别
// 二叉查找树有可能退化成链表，所以一般使用红黑树(平衡树)来实现，从而保持性能稳定
class MedianFinder4 {

    // TreeSet 底层使用红黑树实现
    private TreeSet<int[]> data;
    // 节点比较器
    private Comparator<int[]> customComparator;
    // index 用于记录数据流中每个元素的位置
    int index;

    // lower 表示两个中间元素小的那个
    // upper 表示两个中间元素大的那个
    private int[] lower, upper;

    /*
     比如
     元素：1,2,3,4,5
     索引：0,1,2,3,4

     其中 lower = upper = [3,2]
     -> 第一个值是元素值 3
     -> 第二个值是该元素数据流中的索引位置

     比如
     元素：1,2,3,4,5,6
     索引：0,1,2,3,4,5

     其中 lower = [3,2]
          upper = [4,3]
     */

    public MedianFinder4() {
        // 自定义比较器，Lambda 表达式整体作为 Comparator 进行赋值
        customComparator = (a, b) -> {
            if (a[0] != b[0]) {
                // 节点值升序排列
                return Integer.compare(a[0], b[0]);
            } else {
                // 节点值相同，索引值升序排列
                // => 处理节点存在重复元素的情况
                return Integer.compare(a[1], b[1]);
            }
        };
        // TreeSet，构造方法中传入自定义比较器，按照自定义逻辑比较
        data = new TreeSet<>(customComparator);

        lower = null;
        upper = null;
        index = 0;
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：log(n)
    public void addNum(int num) {
        // 记录当前的元素值及其索引位置
        int[] curr = new int[]{num, index++};
        // 将 curr 加入 data 集合中
        data.add(curr);

        // 在 data 集合，添加 curr 之后，需要维护 lower 和 upper 指针
        // 1. data 只有一个元素，lower 和 upper 都指向该元素
        if (data.size() == 1) {
            lower = data.first();
            upper = data.first();
            // 2.lower 和 upper 相等，说明上一次数据流中有奇数个元素
            //   加上当前需要处理元素，此时元素的个数变成了偶数
        } else if (customComparator.compare(lower, upper) == 0) {
            // 2.1 curr < lower => lower 指针移动到小于 lower 的前驱节点
            if (customComparator.compare(curr, lower) < 0) {
                lower = data.lower(lower);
            } else {
                // 2.2 curr > lower => upper 指针移动到大于 upper 的后继节点
                upper = data.higher(upper);
            }
        } else {
            // 3. lower 和 upper 不相等的话，说明上一次数据流中有偶数个元素
            //    加上当前需要处理元素，此时元素的个数变成了奇数
            // 3.1 curr < lower => lower 指针 和 upper 指针，指向 lower
            if (customComparator.compare(curr, lower) < 0) {
                upper = lower;
                // 3.2 curr > upper => lower 指针 和 upper 指针，指向 upper
            } else if (customComparator.compare(curr, upper) > 0) {
                lower = upper;
            } else {
                // 3.3 lower < curr < upper => lower 指针 和 upper 指针，指向 curr
                lower = curr;
                upper = curr;
            }
        }
    }

    // 返回目前所有元素的中位数
    // 时间复杂度：O(1)
    public double findMedian() {
        // 防止没有添加数据，然后调用这个方法的情况
        if (lower == null) {
            return 0.0;
        }
        return (lower[0] + upper[0]) * 0.5;
    }
}

// 进阶 1：如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
// KeyPoint 数据范围小，且涉及排序 => 计数排序
class MedianFinder5 {
    // 计数排序数组
    private int[] count;
    // 记录当前添加元素个数
    private int size;

    public MedianFinder5() {
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
        // cnt 表示到当前数字为止，一共出现的数字的个数
        int cnt = 0;
        // prevCnt 表示到前一个数字为止，一共出现的数字的个数
        int prevCnt = 0;
        // 偶数
        boolean isEven = size % 2 == 0;

        // 记录上一个非零元素
        int lastNoneZero = 0;
        int lower = 0, upper = 0;

        // 遍历 count 中每个位置
        for (int num = 0; num < 101; num++) {
            cnt += count[num];
            // KeyPoint 数组无论奇偶，排完序后 upper 都是在 (size/2+1) 个元素位置
            if (cnt >= size / 2 + 1) {
                // upper 必然能确定
                upper = num;

                // KeyPoint bug 修复：偶数并且中间两个元素不是同一个元素

                // 比如：数组 arr1 [1,2,2,2,2,2,2,8]，此时 upper = num = 2
                // 若按照之前的逻辑，因为数组 arr1 个数是偶数，所以 upper = num, lower = lastNoneZero，即 upper = 2, lower = 1
                // 这是不对的，因为中间的两个元素都是 2，所以不能单纯的考虑是偶数
                // 还需要加上 prevCnt + 1 == cnt，因为只有 prevCnt + 1 == cnt 可以确定中间两个元素一定不是相同的

                // 比如：数组 arr2 [1,1,2,3,4,5] 这个时候 upper = num = 3
                // cnt = 4 => 到当前数字 3 为止，一共出现了 4 个数字
                // prevCnt = 3 => 到前一个数字 2 为止，一共出现了 3 个数字
                // 此时 prevCnt + 1 == cnt，表明中间的两个数字不相同，可以使用 low = lastNoneZero

                // 比如：数组 arr3 [1,2,2,2,2,2,2,8]，这个时候 upper = num = 2
                // cnt = 7 => 到当前数字 2 为止，一共出现了 7 个数字
                // prevCnt = 1 => 到前一个数字 1 为止，一共出现了 1 个数字
                // 此时 prevCnt + 1 != cnt，表明中间的两个数字相同，既然相同的话，那么 lower 和 upper 相等

                if (isEven && prevCnt + 1 == cnt) {
                    lower = lastNoneZero;
                } else {
                    // 两种情况都是 lower = upper
                    // 1. isEven && prevCnt + 1 != cnt
                    // 2. !isEven => 数组奇数个元素，upper 和 lower 是同一个位置
                    lower = upper;
                }
                break;
            }
            // 记录上一个非零元素，
            if (count[num] > 0) lastNoneZero = num;
            // 赋值 prevCnt，为下轮循环做准备
            prevCnt = cnt;
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

// 进阶 2：如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
// KeyPoint
//  分析：99% 的整数都在 0 到 100 范围内，意味着中位数肯定是在 [0，100] 内
//       => 我们要做的就是统计好有多少个小于 0 的元素，有多少个大于 100 的元素
//       => 不是很明白
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
        int lower = 0;
        int upper = 0;
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
