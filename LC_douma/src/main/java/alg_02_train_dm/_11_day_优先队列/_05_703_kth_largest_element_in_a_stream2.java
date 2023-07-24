package alg_02_train_dm._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:57
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream2 {

    // KeyPoint 方法二 一次全排序 + 新增元素插入排序
    class kthLargest {
        List<Integer> list;
        int k;

        // 时间复杂度：O(nlogn)
        public kthLargest(int k, int[] nums) {
            list = new ArrayList<>();
            this.k = k;
            for (int num : nums) list.add(num);
            // 1.一次全排序
            // 初始化使用全排序，因为构造方法故只是执行一次
            // 故即使一开始时间复杂度高一点也是可以接受的
            Collections.sort(list);
        }

        // 时间复杂度：O(n)
        public int add(int val) {

            // 2.新增元素插入排序
            // 原来 list 集合中数据已经有序了，通过插入排序方式来维护新插入元素的顺序
            // 即通过从后往前遍历 list 集合，找到一个合适的位置，将 val 插入，时间复杂度 O(n)

            // 更加严谨，list 其实不会为空的
            if (list.isEmpty()) {
                list.add(val);
            } else {
                // bug 修复：先记住之前的数组的长度
                int size = list.size();

                // KeyPoint 实现扩容一个位置
                // 通过 list 结尾 add 一个 Integer.MIN_VALUE，后续 Integer.MIN_VALUE 会被覆盖掉
                // 并没有对 data 进行排序操作
                list.add(Integer.MIN_VALUE);
                int j = size;
                // 后往前遍历 list 集合，找到一个合适的位置
                // 注意：需要 j > 0，因为涉及 j-1，否则数据越界
                for (; j > 0; j--) {
                    // [j] 为新加入的 Integer.MIN_VALUE
                    // val < [j-1]
                    if (val < list.get(j - 1)) {
                        // 将 [j-1] 赋值到 [j]
                        // => 实现 [j-1] 向后移动一个位置，从而空出一个位置
                        list.set(j, list.get(j - 1));
                    } else {
                        // val >= [j-1]
                        // j-1 位置元素不变化，需要插入的位置为 j
                        break;
                    }
                }
                // 找到正确位置，赋值操作
                list.set(j, val);
            }

            // 不能使用一开始 size，因为 list 集合 add 元素之后，list 的 size 就发生了变化
            return list.get(list.size() - k);
        }
    }
}
