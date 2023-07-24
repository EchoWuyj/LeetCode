package alg_02_train_dm._11_day_优先队列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 17:02
 * @Version 1.0
 */
public class _05_703_kth_largest_element_in_a_stream1 {

     /*  
         703 数据流中的第 k 大元素
         设计一个找到数据流中第 k 大元素的类(class)。
         注意：是排序后的第 k 大元素，不是第 k 个不同的元素。

         请实现 kthLargest 类：
          1.kthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
          2.int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。

         输入：
         ["kthLargest", "add", "add", "add", "add", "add"]
         [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]

         输出：
         [null, 4, 5, 5, 8, 8]

         解释：
         kthLargest kthLargest = new kthLargest(3, [4, 5, 8, 2]);
         kthLargest.add(3);   // return 4
         kthLargest.add(5);   // return 5
         kthLargest.add(10);  // return 5
         kthLargest.add(9);   // return 8
         kthLargest.add(4);   // return 8

         提示
         1 <= k <= 10^4
         0 <= nums.length <= 10^4
         -10^4 <= nums[i] <= 10^4
         -10^4 <= val <= 10^4
         最多调用 add 方法 10^4 次 => add 最多调用 10^4 次该方法
         题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
     */

    // keyPoint 方法一 动态数组 + 排序
    class kthLargest {
        // KeyPoint 定义成员变量，后续多个方法都是可以调用成员变量
        // 1.对数据流排序前，思考使用什么来存储数据流
        // 2.数据流因为添加元素个数不确定，故使用动态数组
        private List<Integer> list;
        private int k;

        // 时间复杂度：O(n)
        public kthLargest(int k, int[] nums) {
            list = new ArrayList<>();
            this.k = k;
            for (int num : nums) list.add(num);
        }

        // 时间复杂度：O(nlogn)
        public int add(int val) {
            list.add(val);
            // 对集合排序，升序排列
            // => 全量排序，性能瓶颈，后续优化
            Collections.sort(list);
            // 数据流中的第 k 大元素
            // => 即为倒数第 k 个，对应 data.size() - k
            return list.get(list.size() - k);
        }
    }
}




