package alg_02_train_wyj._21_day_综合应用二;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 13:05
 * @Version 1.0
 */
public class _03_220_contains_duplicate_iii {

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null) return false;
        int left = 0;
        int right = 0;
        TreeSet<Long> window = new TreeSet<>();
        while (right < nums.length) {
            long x = nums[right];
            Long y = window.ceiling(x - t);
            if (y != null && y <= x + t) {
                return true;
            }
            window.add((long) nums[right]);
            if (window.size() > k) {
                window.remove((long) nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null) return false;
        int left = 0, right = 0;
        long bucketSize = t + 1L;

        Map<Long, Long> window = new HashMap<>();
        while (right < nums.length) {
            long x = nums[right];
            long bucketId = getBucketId(x, bucketSize);

            if (window.containsKey(bucketId)) return true;
            long leftBucketId = bucketId - 1;
            long rightBucketId = bucketId + 1;

            if (window.containsKey(leftBucketId)
                    && x - window.get(leftBucketId) <= t) return true;
            if (window.containsKey(rightBucketId)
                    && window.get(rightBucketId) - x <= t) return true;

            window.put(bucketId, x);

            if (window.size() > k) {
                window.remove(getBucketId(nums[left], bucketSize));
                left++;
            }
            right++;
        }
        return false;
    }

    public long getBucketId(long x, long bucketSize) {
        if (x >= 0) return x / bucketSize;
        return (x + 1) / bucketSize - 1;
    }
}
