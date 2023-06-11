package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 13:46
 * @Version 1.0
 */

// 39. 组合总和 1
public class _06_39_CombinationSum {
    /*
        给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
        找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
        并以列表形式返回。你可以按 任意顺序 返回这些组合。

        candidates 中的 同一个 数字可以 无限制重复被选取 。
        如果至少一个数字的被选数量不同，则两种组合是不同的。
        对于给定的输入，保证和为 target 的不同组合数少于 150 个。

        输入：candidates = [2,3,6,7], target = 7
        输出：[[2,2,3],[7]]
        解释：
        2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
        7 也是一个候选， 7 = 7 。
        仅有这两种组合。

     */

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        // start 传入的是:索引，因为方法中给定的是数组，不是具体数值
        findCombinationSum(nums, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void findCombinationSum(int[] nums,
                                    int startIndex,
                                    // 每个节点目标和 => 逆向相减
                                    int target,
                                    List<Integer> combination,
                                    List<List<Integer>> res) {
        // 剪枝 -> 递归边界
        // 回溯，往回上一层，不需要继续向下递归遍历
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 将数值放进 combination，而不是索引
            combination.add(nums[i]);
            // 因为同一个数字可以无限制重复被选，所以剪枝过程不是严格大于，而是可以有等于的
            // 子节点 >= 父节点
            findCombinationSum(nums, i, target - nums[i], combination, res);
            // 回溯的过程中，将当前的节点从 combination 中删除
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 5};
        System.out.println(new _06_39_CombinationSum().combinationSum(data, 8));
        // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
    }
}
