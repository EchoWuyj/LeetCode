package alg_02_train_wyj._21_day_综合应用二;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 10:31
 * @Version 1.0
 */
public class _01_217_contains_duplicate {

    public boolean containsDuplicate1(int[] nums) {
        if (nums == null) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        if (nums == null) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate3(int[] nums) {
        if (nums == null) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

    public boolean containsDuplicate4(int[] nums) {
        if (nums == null) return false;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
