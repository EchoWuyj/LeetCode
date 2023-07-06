package alg_02_train_wyj._06_day_位运算;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 20:08
 * @Version 1.0
 */
public class _08_137_single_number_ii {
    public int singleNumber1(int[] nums) {
        Set<Integer> once = new HashSet<>();
        Set<Integer> twice = new HashSet<>();

        for (int num : nums) {
            if (once.contains(num)) {
                once.remove(num);
            } else if (!twice.contains(num)) {
                once.add(num);
            }

            if (twice.contains(num)) {
                twice.remove(num);
            } else if (!once.contains(num)) {
                twice.add(num);
            }
        }
        return once.iterator().next();
    }

    public int singleNumber2(int[] nums) {
        int once = 0, twice = 0;
        for (int num : nums) {
            once = (once ^ num) & ~twice;
            twice = (twice ^ num) & ~once;
        }
        return once;
    }

    public int singleNumber3(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int oneCount = 0;
            for (int num : nums) {
                oneCount += (num >> i) & 1;
            }

            if (oneCount % 3 == 1) {
                res |= (1 << i);
            }
        }
        return res;
    }
}
