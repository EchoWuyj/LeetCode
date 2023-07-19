package alg_02_train_wyj._01_day_数组技巧;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 13:31
 * @Version 1.0
 */
public class _01_442_find_all_duplicates_in_an_array {
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) res.add(Math.abs(nums[i]));
            else nums[index] = -nums[index];
        }
        return res;
    }

    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n) res.add(i + 1);
        }
        return res;
    }
}
