package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 20:43
 * @Version 1.0
 */
public class _10_01_twoSum {

    // 两数之和(梦开始的地方！)
    // HashMap
    public int[] twoSum(int[] nums, int target) {
        // 特判
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        // key：数组值
        // value：索引
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            // 中间判断
            int num2 = target - num1;
            // 判断 map 中是否存在 num2
            if (map.containsKey(num2)) {
                int index = map.get(num2);
                return new int[]{i, index};
            }
            // 加入 map，记录 num1 和 对应索引 i
            map.put(num1, i);
        }
        // 否则没有，返回 null
        return null;
    }
}
