package alg_02_train_dm._22_day_回溯算法一_二刷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 16:27
 * @Version 1.0
 */
public class _10_90_Subsets2 {
    /*
        90.子集II
        给你一个整数数组 nums ，其中 可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
        解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

        示例 1：
        输入：nums = [1,2,2]
        输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]

        提示：
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10

     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums,
                     int start,
                     List<Integer> subset,
                     List<List<Integer>> res) {

        res.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            // i >= start + 1，为了排除 i == start 的情况，也就是保证 i 不是每层的第一个子节点
            // 因为第一个子节点前面没有节点，则 nums[i] == nums[i - 1] 就没有意义，且 nums[i-1] 会数组越界
            if (i >= start + 1 && nums[i] == nums[i - 1]) continue;
            subset.add(nums[i]);
            dfs(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _10_90_Subsets2().subsetsWithDup(new int[]{1, 2, 2}));
    }
}
