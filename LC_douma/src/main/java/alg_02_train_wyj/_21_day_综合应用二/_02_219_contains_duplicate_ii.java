package alg_02_train_wyj._21_day_综合应用二;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 10:51
 * @Version 1.0
 */
public class _02_219_contains_duplicate_ii {

    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] && j - i <= k) return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null) return false;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                if (Math.abs(i - j) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (nums == null) return false;
        int left = 0, right = 0;
        int n = nums.length;
        while (right < n) {
            left = Math.max(0, right - k);
            while (left < right) {
                if (nums[left] == nums[right]) return true;
                left++;
            }
            right++;
        }
        return false;
    }

    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        if (nums == null) return false;
        int n = nums.length;
        int left = 0, right = 0;
        HashSet<Integer> set = new HashSet<>();
        while (right < n) {
            if (set.contains(nums[right])) return true;
            set.add(nums[right]);
            while (set.size() >= k + 1) {
                set.remove(nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }
}
