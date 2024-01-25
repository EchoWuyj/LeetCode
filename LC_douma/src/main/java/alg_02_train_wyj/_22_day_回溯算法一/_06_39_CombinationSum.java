package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 14:19
 * @Version 1.0
 */
public class _06_39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        dfs(nums, target, 0, path, res);
        return res;
    }

    public void dfs(int[] nums, int target, int index,
                    List<Integer> path, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
        }

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, target - nums[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }
}
