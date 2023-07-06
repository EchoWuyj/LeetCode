package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 14:17
 * @Version 1.0
 */
public class _07_40_CombinationSum2 {

    /*
        40. 组合总和 II
        给定一个候选人编号的集合 candidates 和一个目标数 target
        找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的每个数字在每个组合中只能使用 一次 。
        注意：解集不能包含重复的组合。
        
        输入: candidates = [10,1,2,7,6,1,5], target = 8
        输出:
            [
            [1,1,6],
            [1,2,5],
            [1,7],
            [2,6]
            ]

        提示:
        1 <= candidates.length <= 100
        1 <= candidates[i] <= 50
        1 <= target <= 30
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        // 排序：将相同元素放在一起，紧紧挨着
        Arrays.sort(nums);
        dfs(nums, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums,
                     int start,
                     int target,
                     List<Integer> combination,
                     List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 为了避免索引越界，所以 i >= start + 1，nums[i] == nums[i - 1] 直接跳过，避免重复
            // 不能单纯是 i >= 1，因为每次 for 循环，i 都是从 start 开始的，并不固定从 1 开始
            // 有 子节点索引 > 父节点索引，所以不需 visit 来标记是否被访问
            if (i >= start + 1 && nums[i] == nums[i - 1]) continue;
            // 先进行 if 判断，之后再去 add 操作，逻辑关系不要搞反了
            combination.add(nums[i]);
            // 子节点索引 > 父节点索引  => 避免重复
            dfs(nums, i + 1, target - nums[i], combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
