package alg_03_high_frequency._01_codetop.top_100;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _100_560_subarraySum {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for (int i = 0; i < n + 1; i++) {
            int diff = prefixSum[i] - k;
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return res;
    }
}
