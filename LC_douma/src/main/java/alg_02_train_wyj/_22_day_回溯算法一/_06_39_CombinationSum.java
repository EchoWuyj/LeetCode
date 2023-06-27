package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 14:19
 * @Version 1.0
 */
public class _06_39_CombinationSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> combinationSum = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        dfs(nums, 0, target);
        return res;
    }

    public void dfs(int[] nums, int start, int target) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combinationSum));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            combinationSum.add(nums[i]);
            dfs(nums, i, target - nums[i]);
            combinationSum.remove(combinationSum.size() - 1);
        }
    }
}
