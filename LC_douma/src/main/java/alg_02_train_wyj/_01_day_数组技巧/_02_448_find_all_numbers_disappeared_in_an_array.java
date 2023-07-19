package alg_02_train_wyj._01_day_数组技巧;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 14:32
 * @Version 1.0
 */
public class _02_448_find_all_numbers_disappeared_in_an_array {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) res.add(i + 1);
        }
        return res;
    }
}
