package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 13:46
 * @Version 1.0
 */

public class _06_39_CombinationSum {
    /*
        39. 组合总和 1
        给你一个 无重复元素 的 整数 数组 candidates 和一个目标整数 target ，
        找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
        并以列表形式返回。你可以按 任意顺序 返回这些组合。

        candidates 中的 同一个 数字可以 无限制重复被选取
        如果 至少一个数字的被选数量不同，则两种组合是不同的。
        对于给定的输入，保证和为 target 的不同组合数少于 150 个。

        输入：candidates = [2,3,6,7], target = 7
        输出：[[2,2,3],[7]]
        解释：
        2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
        7 也是一个候选， 7 = 7 。
        仅有这两种组合。

        输入: candidates = [2,3,5], target = 8
        输出: [[2,2,2,2],[2,3,3],[3,5]]

        说明： [3,5] 和 [5,3] 两个属于相同组合

        提示：
        1 <= candidates.length <= 30
        2 <= candidates[i] <= 40
        candidates 的所有元素 互不相同
        1 <= target <= 40

     */

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 不需要特判，题目限制条件排除了这种情况
        if (nums == null || nums.length == 0)
            return res;
        // 传入的 start 是 nums 索引，不是具体数值
        dfs(nums, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums,
                     int start,
                     // 每个节点目标和 => 逆向相减
                     int target,
                     List<Integer> combination,
                     List<List<Integer>> res) {
        // 递归边界，往回上一层
        // 注意：本题需要额外一个递归边界条件，有可能 nums 中元素无法组成 target
        // 若单是一个 target == 0，则递归栈溢出了
        if (target < 0) return;

        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 添加的是数值，而不是索引
            combination.add(nums[i]);
            // 因为同一个数字可以无限制重复被选，所以剪枝过程 不是严格大于，而是可以有等于
            // 子节点 >= 父节点
            dfs(nums, i, target - nums[i], combination, res);
            combination.remove(combination.size() - 1);
        }
    }
}
