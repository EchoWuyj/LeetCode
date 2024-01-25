package alg_02_train_wyj._09_day_哈希查找;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:34
 * @Version 1.0
 */
public class _03_128_longest_consecutive_sequence {
    public int longestConsecutive1(int[] nums) {
        if (nums.length < 2) return nums.length;
        Arrays.sort(nums);

        int res = 1;
        int count = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 1;
            }
        }
        return Math.max(res, count);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 1;
        for (int num : nums) {
            int count = 1;
            int cur = num;
            if (set.contains(cur - 1)) continue;
            while (set.contains(cur + 1)) {
                count++;
                cur++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
