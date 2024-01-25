package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 13:53
 * @Version 1.0
 */
public class _11_41_first_missing_positive {
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            boolean isExist = false;
            for (int j = 0; j < n; j++) {
                if (i == nums[j]) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) return i;
        }
        return n + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) return i;
        }
        return n + 1;
    }

    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }
}
