package alg_02_train_wyj._06_day_位运算;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 16:30
 * @Version 1.0
 */
public class _07_136_single_number {

    public int singleNumber1(int[] nums) {
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            if (res.contains(num)) {
                res.remove(num);
            } else {
                res.add(num);
            }
        }
        return res.iterator().next();
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
