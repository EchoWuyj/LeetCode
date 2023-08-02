package alg_02_train_dm._21_day_综合应用二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-02 14:10
 * @Version 1.0
 */
public class _03_220_contains_duplicate_iii2 {

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
