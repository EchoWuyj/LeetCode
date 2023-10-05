package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-07-07 11:25
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k2 {
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        for (int i = 0; i < n + 1; i++) {
            int diff = prefixSum[i] - k;
            for (int j = 0; j < i; j++) {
                if (prefixSum[j] == diff) res++;
            }
        }
        return res;
    }

    public int subarraySum2(int[] nums, int k) {
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

    public int subarraySum3(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            int diff = prefixSum - k;
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}
