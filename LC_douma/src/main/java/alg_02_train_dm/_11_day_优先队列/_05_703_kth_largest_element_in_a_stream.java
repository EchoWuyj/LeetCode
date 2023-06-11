package alg_02_train_dm._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 17:02
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream {

     /*  
            703 号算法题：数据流中的第 K 大元素
            设计一个找到数据流中第 k 大元素的类（class）。
            注意是排序后的第 k 大元素，不是第 k 个不同的元素。

            请实现 KthLargest 类：
                1. KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
                2. int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。

            输入：
                ["KthLargest", "add", "add", "add", "add", "add"]
                [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
            输出：
                [null, 4, 5, 5, 8, 8]

            解释：
                KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
                kthLargest.add(3);   // return 4
                kthLargest.add(5);   // return 5
                kthLargest.add(10);  // return 5
                kthLargest.add(9);   // return 8
                kthLargest.add(4);   // return 8

            1 <= k <= 10^4
            0 <= nums.length <= 10^4
            -10^4 <= nums[i] <= 10^4
            -10^4 <= val <= 10^4
            最多调用 add 方法 10^4 次 => add 最多调用 10^4 次该方法
            题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
     */
}

// KeyPoint 方法一 动态数组 + 排序
class KthLargest1 {
    private List<Integer> data;
    private int k;

    // 时间复杂度：O(n)
    public KthLargest1(int k, int[] nums) {
        data = new ArrayList<>();
        this.k = k;
        for (int num : nums) data.add(num);
    }

    // 时间复杂度：O(nlogn)
    public int add(int val) {
        data.add(val);
        // 对集合排序，升序排列
        // KeyPoint => 全量排序，性能瓶颈，后续优化
        Collections.sort(data);
        // 数据流中的第 K 大元素 => 即为倒数第 k 个，对应 data.size() - k
        return data.get(data.size() - k);
    }
}

// KeyPoint 方法二 一次全排序 + 新增元素插入排序
class KthLargest2 {
    private List<Integer> data;
    private int k;

    // 时间复杂度：O(nlogn)
    public KthLargest2(int k, int[] nums) {
        this.data = new ArrayList<>();
        this.k = k;
        for (int num : nums) data.add(num);
        // 初始化使用全排序，因为构造方法故只是执行一次，即使时间复杂度高一点也是可以接受的
        Collections.sort(data);
    }

    // 时间复杂度：O(n)
    public int add(int val) {
        // 更加严谨，data 其实不会为空的
        if (data.isEmpty()) {
            data.add(val);
        } else {

            // 插入排序
            // => 原来 data 集合中数据已经有序了，通过插入排序方式来维护新插入元素的顺序

            // bug 修复：先记住之前的数组的长度
            int n = data.size();

            // KeyPoint 扩容一个位置
            // 仅仅是在 data 结尾 add Integer.MIN_VALUE，并没有对 data 进行排序操作
            data.add(Integer.MIN_VALUE);
            int j = n;
            for (; j > 0; j--) {
                // val < [j-1]
                if (val < data.get(j - 1)) {
                    // 将 [j-1] 赋值到 [j] => [j-1] 向后移动一个位置
                    data.set(j, data.get(j - 1));
                    // val >= [j-1]
                } else {
                    break;
                }
            }
            // 找到正确位置，赋值操作
            data.set(j, val);
        }
        return data.get(data.size() - k);
    }
}

// KeyPoint 方法三 小顶堆
// => 优化：不需要对整个数据流中所有数据都去维护数据，无非是想获取第 k 个大元素，其他元素可以不用考虑
class KthLargest {
    private PriorityQueue<Integer> data;
    private int k;

    // 时间复杂度：O(nlogk)
    public KthLargest(int k, int[] nums) {
        data = new PriorityQueue<>(k);
        this.k = k;
        for (int num : nums) {
            // KeyPoint 调用 add 方法，将元素加入进去，而不是 data.add(num)
            add(num);
        }
    }

    // 时间复杂度：O(logk)
    public int add(int val) {
        if (data.size() < k) {
            data.add(val);
            // data.size() >= k，对第 k 个元素进行判断操作
            // val 大于堆顶，才去调整堆结构，否则直接跳过
        } else if (val > data.peek()) {
            data.remove();
            data.add(val);
        }
        return data.peek();
    }
}
