package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 11:28
 * @Version 1.0
 */
public class _13_456_132_pattern2 {

    // KeyPoint 方法三 线性查找 -> 红黑树 -> O(logn)
    // 时间复杂度 O(nlogn)
    // 数据量：2*10^5，log2(10^5) ≈ 16.6094 < 20
    // O(nlogn)：2*10^5 * log(2*10^5) = 10^6 不会超时
    public static boolean find132pattern3(int[] nums) {

        // KeyPoint 优化
        // 线性查找 O(n) => 二叉查找树(红黑树) O(logn)
        // 目的：查找大于 numsi 的 nums[k]，同时再和 nums[j] 进行比较
        // => 在红黑树中查找大于某个值，时间复杂度 O(logn)
        // => 红黑树本质：二叉查找树

        // KeyPoint 总结
        // 查找操作时间复杂度取决于使用的数据结构
        // => 一开始，数组，只能线性查找
        // => 优化，二叉查找树，O(logn)，降低时间复杂度

        int n = nums.length;
        if (n < 3) return false;
        int numsi = nums[0];

        // KeyPoint TreeMap 底层使用红黑树
        // key => nums[k]
        // value => count

        // KeyPoint 为什么使用 Map?
        // 因为相同的 nums[k] 可能出现多次，为了维护 nums[k] 出现的次数，使用 Map 结构
        // 而不能使用 TreeSet，因为 TreeSet 会去重，去重影响结果正确性
        // 有些情况，重复元素 nums[k] 是正确，但去重之后就有可能不对了，影响结果正确性
        TreeMap<Integer, Integer> numskMap = new TreeMap<>();

        // 将所有可能作为 nums[k] 元素放到红黑树中
        // => i < j < k => k 从 2 开始，前面两个元素留给 nums[i] 和 nums[j]
        //    即从第三个元素开始才可能为 nums[k]
        for (int k = 2; k < n; k++) {
            numskMap.put(nums[k], numskMap.getOrDefault(nums[k], 0) + 1);
        }

        // KeyPoint 类似题目
        // 03_220_contains_duplicate_iii1
        // 本质：红黑树实现区间查找

        // 时间复杂度 O(nlogn)
        // i < j < k => 最后一个留给 k => j 到倒数第二个为止
        // KeyPoint 注意事项
        // 1.通过 j 索引位置限制，优化 for 循环条件
        // 2.同时后续代码存在 j+1，j < n-1，避免索引越界
        for (int j = 1; j < n - 1; j++) {
            // 前置条件：红黑树查找前的判断
            if (nums[j] > numsi) {

                // KeyPoint 补充说明
                // ceilingKey 返回大于或等于给定键的最小键，存在返回，不存在返回 null

                // 红黑树查找大于左边最小值 numsi 得元素
                // => 保证 nums[k] > nums[i]，故得 ceilingKey() 形参传入 numsi+1
                Integer numsk = numskMap.ceilingKey(numsi + 1);
                // 有可能没有，故需要先判空
                if (numsk != null && numsk < nums[j]) return true;
            }

            // 维护最小的 nums[i]
            numsi = Math.min(numsi, nums[j]);
            // KeyPoint 存在 j+1，j < n-1，避免索引越界
            // 遍历完 j 之后，下个元素 nums[j+1] 就不可能作为 nums[k]，需要从红黑树中删除掉
            // 注意：这里删除一个 nums[j+1]，所以只是在次数上减 1，因为 nums[j+1] 可能存在多个
            numskMap.put(nums[j + 1], numskMap.get(nums[j + 1]) - 1); // O(logn)

            // 若 nums[j+1] 对应 count = 0，即出现次数为 0，从 TreeMap 中删除即可
            if (numskMap.get(nums[j + 1]) == 0) numskMap.remove(nums[j + 1]); // O(logn)
        }
        return false;
    }

    public static void main(String[] args) {

        // KeyPoint 补充说明
        // TreeMap.ceilingKey()：获取大于等于指定键的最小键，存在返回，不存在返回 null
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "One");
        treeMap.put(3, "Three");
        treeMap.put(5, "Five");
        treeMap.put(7, "Seven");
        treeMap.put(9, "Nine");

        System.out.println(treeMap.ceilingKey(4)); // 输出：5，最小的大于等于 4 的键是 5
        System.out.println(treeMap.ceilingKey(6)); // 输出：7，最小的大于等于 6 的键是 7
        System.out.println(treeMap.ceilingKey(10)); // 输出：null，大于等于 10 的键不存在，返回null
    }
}
