package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 14:17
 * @Version 1.0
 */
// 40. 组合总和 II
public class _07_40_CombinationSum2 {

    /*
        给定一个候选人编号的集合 candidates 和一个目标数 target
        找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的每个数字在每个组合中只能使用 一次 。
        注意：解集不能包含重复的组合。
        
        输入: candidates = [10,1,2,7,6,1,5], target = 8,
        输出:
            [
            [1,1,6],
            [1,2,5],
            [1,7],
            [2,6]
            ]
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        // 排序，将相同的元素放在一起，紧紧挨着
        Arrays.sort(nums);
        findCombinationSum(nums, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void findCombinationSum(int[] nums,
                                    int startIndex,
                                    int target,
                                    List<Integer> combination,
                                    List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            // 为了避免索引越界，所以 i > startIndex，
            // nums[i] == nums[i - 1] 直接跳过，避免重复
            // 因为有 子节点(索引) > 父节点(索引)，所以不需 visit 来标记是否被访问
            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            // 先进行 if 判断，之后再去 add 操作，逻辑关系不要搞反了
            combination.add(nums[i]);
            // 子节点(索引) > 父节点(索引)  => 避免重复
            findCombinationSum(nums, i + 1, target - nums[i], combination, res);
            // 回溯的过程中，将当前的节点从 combination 中删除
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 2, 1, 2};
        System.out.println(new _07_40_CombinationSum2().combinationSum(data, 5));
        // [[1, 2, 2], [5]]
    }
}
