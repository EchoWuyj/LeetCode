package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 14:40
 * @Version 1.0
 */
public class _07_40_CombinationSum2 {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, target, combination, res);
        return res;
    }

    private void dfs(int[] nums, int start, int target,
                     List<Integer> combination, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            combination.add(nums[i]);
            dfs(nums, i + 1, target - nums[i], combination, res);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 2, 1, 2};
        System.out.println(new _07_40_CombinationSum2().combinationSum(data, 5));
        // [[1, 2, 2], [5]]
    }
}
