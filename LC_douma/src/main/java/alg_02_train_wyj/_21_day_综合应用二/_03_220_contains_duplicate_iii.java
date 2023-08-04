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
        int n = nums.length;
        int left = 0, right = 0;
        TreeSet<Long> window = new TreeSet<>();
        while (right < n) {
            long x = nums[right];
            Long y = window.ceiling(x - t);
            if (y != null && y <= x + t) {
                return true;
            }
            window.add((long) nums[right]);
            while (window.size() >= k + 1) {
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
        long size = t + 1L;
        Map<Long, Long> window = new HashMap<>();
        int n = nums.length;
        while (right < n) {
            long x = nums[right];
            long id = getId(x, size);
            if (window.containsKey(id)) return true;
            long leftId = id - 1, rightId = id + 1;

            if (window.containsKey(leftId)
                    && x - window.get(leftId) <= t) return true;

            if (window.containsKey(rightId)
                    && window.get(rightId) - x <= t) return true;

            window.put(id, x);
            while (window.size() >= k + 1) {
                window.remove(getId(nums[left], size));
                left++;
            }
            right++;
        }
        return false;
    }

    public long getId(long x, long bucketSize) {
        if (x >= 0) return x / bucketSize;
        return ((x + 1) / bucketSize) - 1;
    }
}
