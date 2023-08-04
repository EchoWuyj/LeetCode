package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 11:32
 * @Version 1.0
 */
public class _03_220_contains_duplicate_iii1 {

     /*
        220. 存在重复元素 III
        给你一个整数数组 nums 和两个整数 k 和 t，请你判断是否存在 两个不同下标 i 和 j
        使得 abs(nums[i] - nums[j]) <= t，同时又满足 abs(i - j) <= k 。
        如果存在则返回 true，不存在返回 false。

        示例 1：
        输入：nums = [1,2,3,1], k = 3, t = 0
        输出：true

        示例 2：
        输入：nums = [1,0,1,1], k = 1, t = 2
        输出：true

        示例 3：
        输入：nums = [1,5,9,1,5,9], k = 2, t = 3
        输出：false

        提示：
        0 <= nums.length <= 2 * 10^4
        -2^31 <= nums[i] <= 2^31 - 1
        0 <= k <= 10^4
        0 <= t <= 2^31 - 1

     */

    // KeyPoint 方法一 区间查找 => 滑动窗口 + 有序集合 (平衡二叉搜索树)
    // 时间复杂度：O(nlog(min(n, k)))
    // 平衡二叉搜索树中维护窗口中元素个数
    // 窗口大小，若 n < k，则窗口 n；若 k < n，则窗口 k => min(n, k)
    // 空间复杂度：O(min(n, k))
    // 额外空间 => 平衡二叉搜索树维护窗口中元素个数
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {

        // 结合上一题，条件：abs(i-j) <= k => 联想：滑动窗口
        // 本题在其基础上，附加了 abs(nums[i]-nums[j]) <= t 条件
        // 本题：窗口内区间查找，查找是否存在两个数，使得 abs(nums[i] - nums[j]) <= t

        // 注意：abs(nums[i] - nums[j]) <= t => 区间查找
        // 区别前面题目等值查找，即 nums[i] 和 nums[j] 相等

        // KeyPoint 区间查找
        // 1.暴力求解
        //   使用 left 和 right 指针，线性查找，时间复杂度太高，不推荐

        // 2.平衡二叉搜索树 => 解决：区间查找问题
        // 将 nums[i] = x，nums[j] = y
        // => abs(x-y) <= t，即 x-y <= t 和 y-x <= t
        // => y >= x-t 和 y <= x+t

        // 1.先固定 x，且 t 也是定值，由 y >= x-t，然后在多个满足的 y 中找到最小的 y
        // 2.然后再看看 y 是不是符合 y <= x+t
        // 3.因为 y 足够小，这样满足 y <= x+t 概率才尽可能的大

        // 区间查找场景：平衡二叉搜索树
        // => 二叉搜索树是有序的，在有序数组中最小一个值，该值大于等于某值，速度是非常快的，logn 级别

        // nums：1 33 9 20 14 6 4 8
        //            ↑         ↑
        //            j         i

        // 固定 i，nums[i] = 4，需要在 i 之前，找到 nums[j]，使得 abs(nums[i] - nums[j]) <= t

        // 关键：将 i 之前元素构建成平衡二叉搜索树

        //         9
        //       ↙↘
        //      6   20   => 平衡二叉搜索树
        //    ↙   ↙↘
        //   1    14  33

        // 找最小 y，且 y >= x-t，且 y <= x+t
        // 假设 t=2，x=4，则 y >= x-t = 4-2 = 2，找到一个大于 2 的元素，即 y = 6
        // 且 y <= x+t = 2+4 = 6，则在平衡二叉搜索树 y = 6 是满足要求的
        // => 一个元素这样的操作，时间复杂度 O(logn)
        // => 总的时间复杂度 O(nlogn)

        // KeyPoint 数据越界
        // 凡是遇到这种数据范围 -2^31 <= nums[i] <= 2^31 - 1，就可能存在越界风险，自己得有意识
        // nums：[2147483647,-1,2147483647]，k = 1，t = 2147483647
        // 代码中 y <= x + t，此时 x + t = 2147483647 + 2147483647 必然越界

        int left = 0;
        int right = 0;
        // KeyPoint 包装类型
        // 1.TreeSet 泛型只能使用 Long，不能使用 long
        // 2.window 中 add 和 remove 操作，针对的元素只能是 long 类型
        //   若是 int 类型，需要强制转换，否则报错
        TreeSet<Long> window = new TreeSet<>();
        int n = nums.length;
        while (right < n) { // O(n)

            // KeyPoint 平衡二叉搜索树 实现 窗口内区间查找
            // 固定一个值，将其记做 x
            long x = nums[right];
            // 找到大于等于 x-t 的最小的元素 y，，同时判断，y 是否 <= t+x
            // TreeSet API ceiling() => 找到大于等于 x-t 的最小值，若不存在则返回 null
            Long y = window.ceiling(x - t); // log(min(n, k))
            // 如果 y 存在，并且 y <= x + t，就可以确定  x - y <= t
            if (y != null && y <= x + t) {
                return true;
            }
            // 泛型 Long 限制 => 将转成 long 类型
            window.add((long) nums[right]); // log(min(n, k))
            if (window.size() >= k + 1) {
                // 泛型 Long 限制 => 将转成 long 类型
                window.remove((long) nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }
}
