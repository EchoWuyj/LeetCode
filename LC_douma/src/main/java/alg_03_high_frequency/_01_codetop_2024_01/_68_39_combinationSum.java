package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:40
 * @Version 1.0
 */
public class _68_39_combinationSum {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, target, 0, list, res);
        return res;
    }

    public void dfs(int[] nums, int target, int index,
                    List<Integer> list, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }
        int n = nums.length;
        for (int i = index; i < n; i++) {
            list.add(nums[i]);
            // 注意本题 nums[i] 可以重复选择
            dfs(nums, target - nums[i], i, list, res);
            list.remove(list.size() - 1);
        }
    }
}
