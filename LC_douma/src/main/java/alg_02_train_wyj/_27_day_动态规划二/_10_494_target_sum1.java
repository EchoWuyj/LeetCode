package alg_02_train_wyj._27_day_动态规划二;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-06-09 20:02
 * @Version 1.0
 */
public class _10_494_target_sum1 {

    private int res;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, target);
        return res;
    }

    public void dfs(int[] nums, int index, int target) {
        if (index == nums.length) {
            if (target == 0) res++;
            return;
        }

        dfs(nums, index + 1, target - nums[index]);
        dfs(nums, index + 1, target + nums[index]);
    }

    public int findTargetSumWays1(int[] nums, int target) {
        HashMap<String, Integer> map = new HashMap<>();
        return dfs1(nums, 0, target, map);
    }

    private int dfs1(int[] nums, int index, int target, HashMap<String, Integer> map) {
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        // 将数组转成 String
        String key = Arrays.toString(new int[]{target, index});

        if (map.containsKey(key)) return map.get(key);
        int left = dfs1(nums, index + 1, target - nums[index], map);
        int right = dfs1(nums, index + 1, target + nums[index], map);

        map.put(key, left + right);
        return map.get(key);
    }
}
