package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:38
 * @Version 1.0
 */
public class _06_295_find_median_from_data_stream4 {

    // KeyPoint 方法四 二叉查找树 => 红黑树
    // 二叉查找树 => 左子节点，父节点，右子节点，有序
    // 主要用来开阔视野，实现比较复杂，不推荐
    class MedianFinder {

        // KeyPoint 为什么使用红黑树？
        // 1.一般普通的二叉查找树，不能保证做任何操作时间复杂度都是 log 级别
        // 2.二叉查找树有可能退化成链表，所以一般使用红黑树，AVL树 (平衡树)来实现，从而保持性能稳定

        // KeyPoint 额外补充
        // 若面试过程，面试官问，有没有其他实现，再假装若有所思地写出来，学会演戏
        // 但没有必要自己主动写出来，要和面试官聊得开心，没有必要去虐面试官

        // TreeSet 底层使用红黑树实现
        // 泛型：数组
        // => 表示树中节点，即节点通过数组实现
        // => 记录当前的元素值及其索引位置
        TreeSet<int[]> tree;
        // 节点比较器
        Comparator<int[]> comparator;

        // index 用于记录数据流中每个元素位置索引
        int index;

        // 若数组元素偶数个，lower 表示中间两个元素小值，upper 表示中间两个元素大值
        // 若数组元素奇数个，则 lower 和 upper 指向相同元素
        private int[] lower, upper;

         /*
          比如
          元素：1 2 3 4 5
          索引：0 1 2 3 4
                    ↑
                  lower
                    ↑
                  upper

          其中 lower = upper = [3,2]
          => 元素值 3
          => 元素数据流中索引位置

          比如
          元素：1 2 3 4 5 6
          索引：0 1 2 3 4 5
                   ↑ ↑
               lower upper

          其中 lower = [3,2]
               upper = [4,3]
          */

        // 构造方法 => 初始化
        public MedianFinder() {
            // 自定义比较器
            // => Lambda 表达式整体作为 Comparator 进行赋值
            comparator = (a, b) -> {
                // 1.若节点值不同，节点值升序排列
                // 2.若节点值相同，索引值升序排列 => 处理节点存在重复元素的情况
                return a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]);
            };
            // TreeSet 构造方法中传入自定义比较器，按照自定义逻辑比较
            tree = new TreeSet<>(comparator);

            // 定义 lower 和 upper 指针
            lower = null;
            upper = null;
            index = 0;
        }

        // 从数据流中添加一个整数到数据结构中
        // 时间复杂度：log(n)
        public void addNum(int num) {
            // 记录当前的元素值及其索引位置
            int[] cur = new int[]{num, index++};
            // 将 cur 加入 tree 集合中
            tree.add(cur);

            // KeyPoint 在 tree 集合，添加 cur 之后，需要维护 lower 和 upper 指针
            // 1.若 tree 只有一个元素，lower 和 upper 都指向该元素
            if (tree.size() == 1) {
                lower = tree.first();
                upper = tree.first();
                // 2.若 lower 和 upper 相等，说明上一次数据流中有奇数个元素
                //   加上当前需要处理元素，此时元素的个数变成了偶数
            } else if (comparator.compare(lower, upper) == 0) {

                // KeyPoint 注意事项
                // 1.此时 upper 和 lower 指向同一个位置，故使用 lower 和 upper 都可以
                // 2.调用自定义比较器 comparator 进行比较

                // 2.1 cur < lower，则 lower 指针移动到小于 lower 的前驱节点
                // KeyPoint 调用 lower API 方法 => 找 lower 的前驱节点
                if (comparator.compare(cur, lower) < 0) {
                    lower = tree.lower(lower);
                } else {
                    // 2.2 cur > lower，则 upper 指针移动到大于 upper 的后继节点
                    // KeyPoint 调用 higher API 方法 => 找 upper 的后继节点
                    upper = tree.higher(upper);
                }
            } else {
                // 3.lower 和 upper 不相等的话，说明上一次数据流中有偶数个元素
                //   加上当前需要处理元素，此时元素的个数变成了奇数
                // KeyPoint 根据 cur 和 lower，upper 比较，移动 lower 和 upper 指针
                // 3.1 若 cur < lower，则 lower，upper 指向 lower
                if (comparator.compare(cur, lower) < 0) {
                    upper = lower;
                    // 3.2 若 cur > upper，则 lower，upper 指向 upper
                } else if (comparator.compare(cur, upper) > 0) {
                    lower = upper;
                } else {
                    // 3.3 若 lower < cur < upper，则 lower，upper 指向 cur
                    lower = cur;
                    upper = cur;
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
}
