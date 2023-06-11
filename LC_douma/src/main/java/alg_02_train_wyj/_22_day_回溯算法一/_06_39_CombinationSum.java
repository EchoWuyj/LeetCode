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
        List<Integer> combination = new ArrayList<>();
        dfs(nums, 0, target, combination, res);
        return res;
    }

    private void dfs(int[] nums, int startIndex, int target, List<Integer> combination, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            combination.add(nums[i]);
            dfs(nums, i, target - nums[i], combination, res);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 5};
        System.out.println(new _06_39_CombinationSum().combinationSum(data, 8));
        // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
    }
}
