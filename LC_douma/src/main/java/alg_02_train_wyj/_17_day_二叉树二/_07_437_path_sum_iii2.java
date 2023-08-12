package alg_02_train_wyj._17_day_二叉树二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 14:38
 * @Version 1.0
 */
public class _07_437_path_sum_iii2 {

    public static int prefixSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int curSum = 0;
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            res += map.getOrDefault(curSum - target, 0);
            map.put(curSum, map.getOrDefault(curSum - target, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(prefixSum(arr, 5)); // 2
    }
}
