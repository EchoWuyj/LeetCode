package alg_02_train_dm._21_day_综合应用二_二刷;

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

        // 区间查找
        // KeyPoint 3.桶排序 => 更加高效解决区间查找

        // 3.1 桶内判断
        // 将 abs(nums[i] - nums[j]) <= t，放到同一个桶里，每个桶大小设置为 t+1

        // nums：1 7 20 10  t=3，桶大小 size = t+1 = 4
        // nums[i] / (t+1)
        // => 1/4 = 0 号桶
        // => 7/4 = 1 号桶
        // => 20/4 = 5 号桶
        // => 10/4 = 2 号桶

        // nums：0 1 2 3 4 5 6 7 8 9，t = 3，桶大小 size = t+1 = 4
        // nums[i] / (t+1)
        // 0,1,2,3 => 0 号桶，同一个桶内，则 abs(nums[i] - nums[j]) <= t
        // 4,5,6,7 => 1 号桶
        // 8,9 => 2 号桶
        // 通过 t+1，从而保证 3 -0 <= 3

        // 3.2 桶间判断
        // 相邻桶间：前一个桶的最大值，后一个桶的最小值之差 <= t，也是满足题目要求的

        int left = 0;
        int right = 0;

        // 0 <= t <= 2^31 - 1 => t 可能存在越界
        long size = t + 1L;

        // Map 映射
        // key => bucketId 桶 id => 也可以理解成每个窗口
        // value => 对应桶中的元素值

        // KeyPoint 注意事项
        // 1.桶内存储的元素值也需要是 long 类型的
        // 2.要不然下面的 x - window.get(leftBucketId) <= t 会越界
        Map<Long, Long> window = new HashMap<>();
        int n = nums.length;
        while (right < n) {
            // 窗口内区间查找
            long x = nums[right];
            long id = getId(x, size);

            // 1.一个桶中有多于 1 个元素值存在
            // => 同一个桶内，则 abs(nums[i] - nums[j]) <= t
            if (window.containsKey(id)) return true;

            // 2.判断相邻的桶中是否存在符合条件的原始值
            long leftId = id - 1, rightId = id + 1;

            // 判断左边相邻桶
            // 1.经过上面 if (window.containsKey(id)) return true;
            // 2.同一个桶中不会有两个元素，否则直接返回 true，故桶内最多只有一个元素
            if (window.containsKey(leftId)
                    && x - window.get(leftId) <= t) return true;

            // 判断右边相邻桶
            // 同理
            if (window.containsKey(rightId)
                    && window.get(rightId) - x <= t) return true;

            // 将元素放入桶中
            window.put(id, x);

            // 维护桶的个数，桶的个数最多为 k+1 个，超过 k+1 个，我们就删除左边的桶
            if (window.size() >= k + 1) {
                window.remove(getId(nums[left], size));
                left++;
            }
            right++;
        }
        return false;
    }

    // 每个 nums[i] 属于那个桶
    private long getId(long x, long size) {
        // KeyPoint 注意：x 正负处理逻辑不同，故需要对 x 进行正负判断
        // 如：0 1 2 3 为 0 号桶
        // 如：-4,-3,-2,-1 应该是 -1 号桶
        // => 若使用 x / size，则不是对应 -1 号桶
        // => 解决：负数每项 + 1 / size => bucketId，再 bucketId 减 1
        // -4,-3,-2,-1，每项 + 1 => -3,-2,-1,0
        // -3,-2,-1,0 / size = 0 桶，bucketId -1 = -1

        if (x >= 0) return x / size;
        return ((x + 1) / size) - 1;
    }
}
