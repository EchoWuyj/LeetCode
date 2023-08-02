package alg_02_train_dm._21_day_综合应用二;

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

        KeyPoint 数据可能越界
        凡是遇到这种数据范围 -2^31 <= nums[i] <= 2^31 - 1，就可能存在越界风险，自己得有意识

        nums：[2147483647,-1,2147483647]
        k = 1
        t = 2147483647
        代码 y <= x + t，中 x + t
        x + t = 2147483647 + 2147483647 必然越界

     */

    // KeyPoint 方法一 区间查找 => 滑动窗口 + 有序集合 (平衡二叉搜索树)
    // 时间复杂度：O(nlog(min(n, k)))
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {

        // 结合上一题，条件：abs(i-j) <= k => 联想：滑动窗口
        // 本题在其基础上，附加了 abs(nums[i]-nums[j]) <= t

        // 窗口内区间查找，查找是否存在两个数，使得 abs(nums[i] - nums[j]) <= t
        // 将 nums[i] = x，nums[j] = y
        // => abs(x-y) <= t
        // => x-y <= t 和 y-x <= t
        // => y >= x-t 和 y <= x+t

        // 固定 x，然后在 x 的前面找到最小的 y，且 y >= x-t，然后再看看 y 是不是符合 y <= x+t
        // 因为 y 足够小，这样满足 y <= x+t 概率才尽可能的大
        // 该场景使用：平衡二叉搜索树
        // => 二叉搜索树是有序的，在有序数组中最小一个值，该值大于等于某值，速度是非常快的，logn 级别

        // 如 数组：1,33,9,20,14,6,4,8
        //                        ↑
        //                        i

        // 将 i 之前元素构建成，平衡二叉搜索树
        //         9
        //       ↙↘
        //      6   20
        //    ↙   ↙↘
        //   1    14  33
        // 找最小 y，且 y >= x-t，且 y <= x+t
        // 假设 t=2，x=4，则 y >= 2，且 y <= 6 => 则在平衡二叉搜索树 y = 6 是满足要求的
        // => 一个元素这样的操作，时间复杂度 O(logn) => 总的时间复杂度 O(nlogn)

        int left = 0;
        int right = 0;
        TreeSet<Long> window = new TreeSet<>();
        while (right < nums.length) {
            // 窗口内区间查找
            // 固定一个值，将其记做 x
            long x = nums[right];
            // 找到大于等于 x - t 的最小的元素 y，确定 y - x <= t
            // ceiling API  => 找到大于等于 x-t 的最小值，若不存在则返回 null
            Long y = window.ceiling(x - (long) t);
            // 如果 y 存在，并且 y <= x + t，就可以确定  x - y <= t
            if (y != null && y <= x + t) {
                return true;
            }
            // 将转成 long 类型
            window.add((long) nums[right]);

            if (window.size() > k) {
                // KeyPoint bug 修复：需要强转成 long 类型，统一转成 long 类型
                window.remove((long) nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }
}
