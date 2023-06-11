package alg_02_train_dm._21_day_综合应用二;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 11:32
 * @Version 1.0
 */
public class _03_220_contains_duplicate_iii {

     /*
        220. 存在重复元素 III
        给你一个整数数组 nums 和两个整数 k 和 t
        请你判断是否存在 两个不同下标 i 和 j，
        使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
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

        KeyPoint 注意：数据可能越界
        凡是遇到这种数据范围 -2^31 <= nums[i] <= 2^31 - 1，就可能存在越界风险，自己得有意识

        nums：[2147483647,-1,2147483647]
        k = 1
        t = 2147483647
        代码 y <= x + t，中 x + t
        x + t = 2147483647 + 2147483647 必然越界

     */

    // KeyPoint 方法一 区间查找 => 滑动窗口 + 有序集合(平衡二叉搜索树)
    // 时间复杂度：O(nlog(min(n, k)))
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {

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

    // KeyPoint 方法二 区间查找 => 滑动窗口 + 桶
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        // abs(nums[i] - nums[j]) <= t，abs(i - j) <= k

        // 1.桶内判断：
        // 将[i] -[j] <= t 放到同一个桶里，故每个桶大小 t+1
        // 如：数组 0,1,2,3,4,5,6,7,8,9 假设 t=3，桶大小 size = 4
        // 0,1,2,3 => 0 号桶 => 通过 t+1，从而保证 3 -0 <= 3
        // 4,5,6,7 => 1 号桶，8,9 => 2 号桶

        // 2.桶间判断：
        // 相邻桶间，最大值和最小值之差 <= t

        int left = 0;
        int right = 0;

        // 可能越界
        long bucketSize = t + 1L;

        // key => bucketId
        // value => 对应桶中的元素值 => 桶内存储的元素值也需要是 long 类型的
        // 要不然下面的 x - window.get(leftBucketId) <= t 会越界
        Map<Long, Long> window = new HashMap<>();

        while (right < nums.length) {
            // 窗口内区间查找
            long x = nums[right];
            long bucketId = getBucketId(x, bucketSize);

            // 1.一个桶中有多于 1 个元素值存在
            if (window.containsKey(bucketId)) return true;

            // 2.判断相邻的桶中是否存在符合条件的原始值
            long leftBucketId = bucketId - 1, rightBucketId = bucketId + 1;

            // 左边相邻桶
            if (window.containsKey(leftBucketId)
                    && x - window.get(leftBucketId) <= t) return true;

            // 右边相邻桶
            if (window.containsKey(rightBucketId)
                    && window.get(rightBucketId) - x <= t) return true;

            window.put(bucketId, x);

            // 维护桶的个数，桶的个数最多为 k 个，超过 k 个，我们就删除左边的桶
            if (window.size() > k) {
                window.remove(getBucketId(nums[left], bucketSize));
                left++;
            }

            right++;
        }

        return false;
    }

    private long getBucketId(long x, long bucketSize) {
        // x 记性正负判断
        if (x >= 0)
            return x / bucketSize;
        // -4,-3,-2,-1，已经将 0 考虑到正数那一侧
        // 负数整体加 1 / bucketSize => bucketId，再在 bucketId 减 1
        // -4,-3,-2,-1 整体每项 + 1 / 4 => 桶号为 0 和 0,1,2,3 一样
        return ((x + 1) / bucketSize) - 1;
    }
}
