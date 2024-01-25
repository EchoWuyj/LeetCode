package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:23
 * @Version 1.0
 */
public class _81_169_majorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0) + 1;
            if (cnt > n / 2) return num;
            map.put(num, cnt);
        }
        return -1;
    }
}
