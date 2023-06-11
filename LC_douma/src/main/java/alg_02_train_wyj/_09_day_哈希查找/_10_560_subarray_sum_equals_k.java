package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:13
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k {
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int m = i; m <= j; m++) {
                    sum += nums[m];
                }
                if (sum == k) res++;
            }
        }
        return res;
    }

    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    public int subarraySum3(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        for (int i = 1; i <= len; i++) {
            int diff = prefixSum[i] - k;
            for (int j = 0; j < i; j++) {
                if (prefixSum[j] == diff) res++;
            }
        }
        return res;
    }

    public int subarraySum4(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i <= len; i++) {
            int diff = prefixSum[i] - k;
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return res;
    }

    public int subarraySum5(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
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
